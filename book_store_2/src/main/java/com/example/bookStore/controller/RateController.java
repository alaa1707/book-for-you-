package com.example.bookStore.controller;

import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.Offer;
import com.example.bookStore.model.entities.Rate;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.service.BookService;
import com.example.bookStore.service.OfferService;
import com.example.bookStore.service.RateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("bookStore")
public class RateController {
    @Autowired
    private RateService rateService;

    @Autowired
    private BookService bookService;


    @GetMapping("/rates")
    public String showRatesPage(Model model) {
        List<Rate> rates = rateService.getAllRates();
        List<String> encodedImages = rates.stream()
                .map(rate -> bookService.encodeBookImage(rate.getBook()))
                .collect(Collectors.toList());

        model.addAttribute("rates", rates);
        model.addAttribute("encodedImages", encodedImages);
        return "Admin_view/Rate";
    }


// for customers
    @GetMapping("/addRate/{id}")
    public String addRate(@PathVariable int id, @ModelAttribute Rate rate, HttpSession session) {
        Book book = bookService.getBook(id).get();
        User user = (User) session.getAttribute("user");
        rateService.addUserRate(user, book, rate);
        return "redirect:/bookStore/bookDetails/{id}"
                .replace("{id}", String.valueOf(id));
    }

}