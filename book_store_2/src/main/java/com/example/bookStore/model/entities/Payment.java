package com.example.bookStore.model.entities;


import com.example.bookStore.model.utils.PaymentMethod;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalPrice;

    private Date date;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


    public Payment() {
    }

    public Payment(int totalPrice, Date date,PaymentMethod paymentMethod) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.paymentMethod=paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
