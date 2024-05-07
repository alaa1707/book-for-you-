package com.example.bookStore.controller;


import com.example.bookStore.model.EmailWrapper;
import com.example.bookStore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("bookStore")
public class EmailController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/showForgetPassPage")
    public String showForgetPass(Model model) {

        model.addAttribute("email", new EmailWrapper());
        return "Admin_view/forget-password";
    }

    @PostMapping("/forgetPass")
    public String forgetPass(@ModelAttribute("email") EmailWrapper email, Model model) {
        if (accountService.sendEmail(email.getEmail())) {
            model.addAttribute("emailSent", true);
            return "Admin_view/forget-password";
        }
        model.addAttribute("emailError", true);
        return "Admin_view/forget-password";
    }

    public String emailError(@ModelAttribute("email") EmailWrapper email, Model model) {
        model.addAttribute("emailError", true);
        return "Admin_view/forget-password";
    }

    public String emailSuccess(@ModelAttribute("email") EmailWrapper email, Model model) {
        model.addAttribute("emailSent", true);
        return "Admin_view/login";
    }
}








