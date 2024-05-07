package com.example.bookStore.model.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private int numOfBooks;
    private int totalPrice;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public int numOfBooks() {
        return getCartItems().size();
    }

    public Cart(int numOfBooks, int totalPrice, User user) {
        this.numOfBooks = numOfBooks;
        this.totalPrice = totalPrice;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfBooks() {
        return numOfBooks;
    }


    public void setNumOfBooks(int numOfBooks) {
        this.numOfBooks = numOfBooks;
    }

    public int getTotalPrice() {
        int total = 0;
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getBook().getPrice()* cartItem.getQuantity());
        }
        return total;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
