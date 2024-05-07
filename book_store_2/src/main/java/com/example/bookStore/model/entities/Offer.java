package com.example.bookStore.model.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private double discount;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;



    public Book getBook() {
        return book;
    }

    public Offer(double discount, Book book) {
        this.discount = discount;
        this.book = book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public Offer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
