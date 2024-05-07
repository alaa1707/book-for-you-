package com.example.bookStore.controller;

import com.example.bookStore.model.entities.*;
import com.example.bookStore.model.utils.PaymentMethod;
import com.example.bookStore.service.*;
import com.example.bookStore.validation.AddressValidation;
import com.example.bookStore.validation.CreditCardValidation;
import com.example.bookStore.validation.RegisterValidation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("bookStore")
public class PlaceOrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentDetailsService paymentDetailsService;


    @GetMapping("/checkout")
    public String checkout(Address address,Model model) {
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("selectedMethod");
        model.addAttribute("address", address != null ? address : new Address());
        return "Customer_view/chackout";
    }


    @GetMapping("/placeOrder")
    public String placeOrder(
            @ModelAttribute @Valid Address address,
            @RequestParam("selectedMethod") PaymentMethod selectedMethod,
            HttpSession session, Model model) {
        Order order= new Order();
        User user = (User) session.getAttribute("user");
        Cart userCart = cartService.getCart(user.getId()).get();
        if (AddressValidation.isValidAddress(address)) {
             order = orderService.createOrder(address, userCart, selectedMethod, user);
//            orderService.addOrder(order);
            if (order.getPayment().getPaymentMethod() == PaymentMethod.CREDIT_CARD) {
                return "redirect:/bookStore/showPaymentDetailsPage/" + order.getId();
            }
            model.addAttribute("orderId", order.getId());
            model.addAttribute("orderPlaced", true);
        } else {
            checkAddressErrorFields(address, model);
        }

        return checkout(address,model);
    }


    @GetMapping("/showPaymentDetailsPage/{id}")
    public String showPaymentDetailsPage(Model model, @PathVariable int id) {
        model.addAttribute("paymentDetails", new PaymentDetails());
        Order order = orderService.getOrder(id);
        model.addAttribute("order", order);

        return "Customer_view/payment-details";
    }

    @GetMapping("/placeAfterPay")
    public String placeAfterPay(@ModelAttribute Order order, @ModelAttribute PaymentDetails paymentDetails, Model model) {
        Order order1 = orderService.getOrder(order.getId());
        if (orderService.createOrderWithCreditCard(paymentDetails, order1))
            model.addAttribute("orderPlaced", true);
        else
            checkCreditCardErrorFields(paymentDetails, model);
        return "Customer_view/payment-details";

    }


    public void checkAddressErrorFields(Address address, Model model) {

        if (!AddressValidation.isValidCountryOrCity(address.getCity())) {
            model.addAttribute("notValidCity", "not valid city");

        }

        if (!AddressValidation.isValidCountryOrCity(address.getCountry())) {
            model.addAttribute("notValidCountry", "not valid country");
        }

        if (!AddressValidation.isValidAddressField(address.getStreet())) {
            model.addAttribute("notValidStreet", "not valid street");
        }

        if (!AddressValidation.isValidHomeNumber(address.getHomeNumber())) {
            model.addAttribute("notValidHomeNumber", "not valid house number");
        }
    }

    public void checkCreditCardErrorFields(PaymentDetails paymentDetails, Model model) {

        if (!CreditCardValidation.isValidCreditCardNumber(paymentDetails.getCardNumber())) {
            model.addAttribute("notValidCardNumber", "not valid card number");

        }

        if (!CreditCardValidation.isValidCardHolderName(paymentDetails.getCardHolderName())) {
            model.addAttribute("notValidHolder", "not valid card holder name");
        }

        if (!CreditCardValidation.isValidCVV(paymentDetails.getCvv())) {
            model.addAttribute("notValidCVV", "not valid cvv");
        }

        if (!CreditCardValidation.isValidExpirationDate(paymentDetails.getExpirationDate())) {
            model.addAttribute("notValidEXDate", "not valid expiration date");
        }
    }

}
