package com.example.bookStore.controller;


import com.example.bookStore.model.entities.*;
import com.example.bookStore.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("bookStore")
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OfferService offerService;


    @GetMapping("/showCustomerPage")
    public String showCustomerPage(Model model) {
        List<Category> categories= categoryService.getAllCategoriesNames();
        List<Book> topRatesBooks= bookService.topRatesBooks();
        List<Book> allBooks= bookService.getAllBooks();

        model.addAttribute("categories",categories);
        model.addAttribute("topRatesBooks",topRatesBooks);
        model.addAttribute("books",allBooks);


        return "Customer_view/index";
    }

    @GetMapping("/showCategoryBooks/{id}")
    public String showCategoryBooks(@PathVariable int id,Model model) {
        List<Category> categories= categoryService.getAllCategoriesNames();
       Category specificCategory= (categories.stream().filter(category -> category.getId()==id).findFirst()).get();
        model.addAttribute("categoryBooks",specificCategory.getBooks());
        return "Customer_view/specific-category";
    }

    @GetMapping("/offersPage")
    public String showOffersPage(Model model){
        List<Offer> offers = offerService.getAllOffers();
        model.addAttribute("offers", offers);
        return "Customer_view/offers";
    }

    @GetMapping("/showOrdersPage")
    public String showOrdersPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Order> allOrders = orderService.getAllUserOrders(user.getId());
        model.addAttribute("orders", allOrders);
        return "Customer_view/orderHistory";
    }


    @GetMapping("/showContactPage")
    public String showContactPage(Model model){
        model.addAttribute("contact",new Contact());
        return "Customer_view/contact";
    }


    @PostMapping("/addContactMsg")
    public String addContactMsg(@ModelAttribute Contact contact,HttpSession session){
        User user = (User) session.getAttribute("user");
         contactService.addContact(contact,user);
        return "redirect:/bookStore/showContactPage";
    }

    @GetMapping("/showAboutUsPage")
    public String showAboutUsPage(){
        return "Customer_view/about-us";
    }




}
