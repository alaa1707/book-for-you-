package com.example.bookStore.service;


import com.example.bookStore.model.entities.*;
import com.example.bookStore.model.repo.OrderItemRepo;
import com.example.bookStore.model.repo.OrderRepo;
import com.example.bookStore.model.utils.OrderStatus;
import com.example.bookStore.model.utils.PaymentMethod;
import com.example.bookStore.validation.CreditCardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private AddressService addressService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private InvoiceService invoiceService;

    public List<Order> getAllOrders(){
      return orderRepo.findAll();
    }


    public int numOfOrders(){
        return getAllOrders().size();
    }



    public List<Order> getAllUserOrders(int userId){
       return orderRepo.findByUserId(userId);
    }

    public void addOrder(Order order){
        orderRepo.save(order);
    }

    public Order getOrder(int id){
       return orderRepo.findById(id).get();
    }


    public Order createOrder(Address address, Cart userCart, PaymentMethod selectedMethod, User user) {
        Shipment shipment= setShipmentDetails(address);
        Payment payment= setPaymentDetails(shipment,userCart,selectedMethod);

        Order order = new Order(new Date(),payment.getTotalPrice(), user, shipment, payment, OrderStatus.PROCESSING);
        orderRepo.save(order);
        setOrderItems(order,userCart);
        invoiceService.addInvoice(order);

        return order;
    }

    private void setOrderItems(Order order,Cart userCart){

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : userCart.getCartItems()) {
            OrderItem orderItem = new OrderItem(order,cartItem.getBook(), cartItem.getQuantity());
            orderItems.add(orderItem);
            orderItemRepo.save(orderItem);
        }
        order.setOrderItems(orderItems);
    }

    private Payment setPaymentDetails(Shipment shipment, Cart userCart, PaymentMethod selectedMethod){
        Payment payment = new Payment(
                shipment.getPrice() + userCart.getTotalPrice()
                , new Date(), selectedMethod);

        paymentService.addPayment(payment);
        return payment;
    }

    private Shipment setShipmentDetails(Address address){
        addressService.addAddress(address);
        Shipment shipment = new Shipment(50, new Date(), address);
        shipmentService.addShipment(shipment);
        return shipment;
    }

    public boolean createOrderWithCreditCard(PaymentDetails paymentDetails,Order order) {
        if (CreditCardValidation.checkAllFields(paymentDetails)) {
            paymentDetailsService.addPaymentDetails(paymentDetails);
            order.setPaymentDetails(paymentDetails);
            orderRepo.save(order);
            return true;
        }
        return false;
    }
}
