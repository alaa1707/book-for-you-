package com.example.bookStore.controller;

import com.example.bookStore.model.entities.Order;
import com.example.bookStore.service.BookService;
import com.example.bookStore.service.InvoiceService;
import com.example.bookStore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("bookStore")
public class DashboardController {

    @Autowired
    private BookService bookService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private OrderService orderService;



    @GetMapping("/showDashboard")
    public String showDashboard(Model model){
        int totalNumOfBooks= bookService.numOfBooks();
        int numOfOrders= orderService.numOfOrders();
        int enquiry= invoiceService.enquiryOfAllInvoices();
        List<Order> latestOrders = orderService.getAllOrders().stream()
                .sorted(Comparator.comparing(Order::getDate).reversed())
                .limit(5)
                .collect(Collectors.toList());


        model.addAttribute("numOfBooks",totalNumOfBooks);
        model.addAttribute("numOfOrders",numOfOrders);
        model.addAttribute("enquiry",enquiry);
        model.addAttribute("orders",latestOrders);

        return "Admin_view/index";
    }



}
