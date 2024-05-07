package com.example.bookStore.controller;


import com.example.bookStore.model.entities.*;
import com.example.bookStore.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("bookStore")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @GetMapping("/showCartPage")
    public String showCartPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Cart userCart = cartService.getCart(user.getId()).get();
        List<CartItem> cartItems = cartService.getAllCartItems(userCart);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", userCart);

        return "Customer_view/cart";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, HttpSession session) {
        Book book = bookService.getBook(id).get();
        User user = (User) session.getAttribute("user");
        cartService.addBookToCart(book, userService.getUser(user.getId()).get(),1);

        return "redirect:/bookStore/showCartPage";
    }

    @GetMapping("/deleteFromCart/{id}")
    public String deleteFromCart(@PathVariable int id, HttpSession session) {
        Book book = bookService.getBook(id).get();
        User user = (User) session.getAttribute("user");
        cartService.removeBookFromCart(book, userService.getUser(user.getId()).get());

        return "redirect:/bookStore/showCartPage";
    }




    @PostMapping("/updateCartItemQuantity/{id}")
    public String updateCartItemQuantity(@RequestParam("quantity") int quantity,@PathVariable int id,HttpSession session){
        User user = (User) session.getAttribute("user");
        Book book= bookService.getBook(id).get();
        cartService.updateCartItemQuantity(user,book,quantity);
        return "redirect:/bookStore/showCartPage";
    }









}
