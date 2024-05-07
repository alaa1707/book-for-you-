package com.example.bookStore.controller;


import com.example.bookStore.model.entities.Order;
import com.example.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("bookStore")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String showOrdersPage(Model model){
        List<Order> orders= orderService.getAllOrders();
        model.addAttribute("orders",orders);
        return "Admin_view/all-booking";
    }
    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        List<Order> orders= orderService.getAllOrders();
        model.addAttribute("orders",orders);
        return "redirect:/bookStore/showDashboard";
    }

}
