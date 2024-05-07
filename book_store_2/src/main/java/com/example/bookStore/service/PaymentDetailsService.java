package com.example.bookStore.service;

import com.example.bookStore.model.entities.PaymentDetails;
import com.example.bookStore.model.repo.PaymentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsService {
    @Autowired
    private PaymentDetailsRepo paymentDetailsRepo;

    public void addPaymentDetails(PaymentDetails paymentDetails){
        paymentDetailsRepo.save(paymentDetails);
    }

    public void savePaymentDetails(PaymentDetails paymentDetails) {
        PaymentDetails existingPaymentDetails = paymentDetailsRepo.findByPaymentId(paymentDetails.getPayment().getId());
        if (existingPaymentDetails != null) {
            // Update the existing PaymentDetails
            existingPaymentDetails.setCardHolderName(paymentDetails.getCardHolderName());
            existingPaymentDetails.setCardNumber(paymentDetails.getCardNumber());
            existingPaymentDetails.setCvv(paymentDetails.getCvv());
            existingPaymentDetails.setExpirationDate(paymentDetails.getExpirationDate());
            paymentDetailsRepo.save(existingPaymentDetails);
        } else {
            // Create a new PaymentDetails
            paymentDetailsRepo.save(paymentDetails);
        }
    }
}
