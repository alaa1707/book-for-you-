package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails,Integer> {
    PaymentDetails findByPaymentId(int payment_id);
}
