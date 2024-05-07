package com.example.bookStore.service;


import com.example.bookStore.model.entities.Invoice;
import com.example.bookStore.model.entities.Order;
import com.example.bookStore.model.repo.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    public List<Invoice> getAllInvoices(){
        return invoiceRepo.findAll();
    }

    public int enquiryOfAllInvoices(){
        int totalPrice=0;
        for (Invoice invoice: getAllInvoices()){
            int orderPrice= invoice.getOrder().getTotalPrice();
            int shipmentPrice=invoice.getOrder().getShipment().getPrice();

            totalPrice+=(shipmentPrice+orderPrice);
        }
        return totalPrice;
    }

    public void addInvoice(Order order){
        Invoice invoice= new Invoice();
        invoice.setOrder(order);
        invoice.setDate(order.getDate());
        invoice.setTotalPrice(order.getTotalPrice());
        invoiceRepo.save(invoice);
    }

    public Optional<Invoice> getInvoice(int id){
       return invoiceRepo.findById(id);
    }


}
