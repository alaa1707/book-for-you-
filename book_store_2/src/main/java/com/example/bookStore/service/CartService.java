package com.example.bookStore.service;


import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.Cart;
import com.example.bookStore.model.entities.CartItem;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.model.repo.BookRepo;
import com.example.bookStore.model.repo.CartItemRepo;
import com.example.bookStore.model.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;

    public void addBookToCart(Book book, User user,int quantity){
        Cart cart = user.getCart();

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getBook().equals(book))
                .findFirst()
                .orElse(new CartItem(book, 0, cart));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems()));

        cartRepo.save(cart);
        cartItemRepo.save(cartItem);


    }
    private int calculateTotalPrice(List<CartItem> cartItems) {
        int total_price=0;
        for (CartItem cartItem:cartItems){
             total_price+=(cartItem.getBook().getPrice()*cartItem.getQuantity());
        }
        return total_price;
    }

    public void createCartForUser(User user){
        cartRepo.save(new Cart(0,0,user));

    }


    public Optional<Cart> getCart(int user_id){
        return cartRepo.findByUserId(user_id);
    }


    public void updateCartItemQuantity(User user, Book book, int quantity) {
        CartItem cartItem = cartItemRepo.findByCartIdAndBookId(user.getCart().getId(), book.getId()).get();

        cartItem.setQuantity(quantity);
        cartItem.getCart().setTotalPrice(calculateTotalPrice(cartItem.getCart().getCartItems()));

        cartItemRepo.save(cartItem);
        cartRepo.save(cartItem.getCart());
    }

    public void removeBookFromCart(Book book, User user){
        Cart cart = user.getCart();
        CartItem removedCartItem = cartItemRepo.findByCartIdAndBookId(cart.getId(), book.getId()).get();

        cart.getCartItems().remove(removedCartItem);
        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems()));

        cartItemRepo.delete(removedCartItem);
        cartRepo.save(cart);
    }

    public List<CartItem> getAllCartItems(Cart cart) {
        if (cart == null) {
            return new ArrayList<>();
        }
        return cartItemRepo.findByCartId(cart.getId());
    }
}
