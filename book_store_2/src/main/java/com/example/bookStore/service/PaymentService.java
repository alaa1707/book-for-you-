package com.example.bookStore.service;

import com.example.bookStore.model.entities.Address;
import com.example.bookStore.model.entities.Payment;
import com.example.bookStore.model.repo.AddressRepo;
import com.example.bookStore.model.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    public void addPayment(Payment payment){
        paymentRepo.save(payment);
    }
}
