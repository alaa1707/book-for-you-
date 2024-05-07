package com.example.bookStore.controller;


import com.example.bookStore.model.entities.Account;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.service.AccountService;
import com.example.bookStore.service.CartService;
import com.example.bookStore.service.UserService;
import com.example.bookStore.validation.RegisterValidation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("bookStore")
public class AuthController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/start")
    public String showLoginPage(Model model) {
        model.addAttribute("account", new Account());
        return "Admin_view/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute Account account, Model model, HttpServletRequest request) {
        Optional<Account> existingAccount = accountService.login(account);
        if (existingAccount != null && existingAccount.isPresent()) {
            HttpSession session = request.getSession();
            User user = existingAccount.get().getUser();
            session.setAttribute("user", user);

            String redirectUrl = existingAccount.get().getRole_id() == 0 ? "/bookStore/showCustomerPage" : "/bookStore/showDashboard";
            return "redirect:" + redirectUrl ;
        }
            model.addAttribute("error", true);
            return "Admin_view/login";

    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
      session.invalidate();
        return "redirect:/bookStore/start";
    }

    @GetMapping("/logoutAdmin")
    public String logoutAdmin() {
        return "redirect:/bookStore/start";
    }

    @GetMapping("/showRegisterPage")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("account", new Account());

        return "Admin_view/register";

    }



    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, @ModelAttribute Account account, Model errors) {
        userService.saveUser(user);

        if (accountService.createAccount(account, user)) {
            cartService.createCartForUser(user);
            return "redirect:/bookStore/start";
        }
        checkErrorFields(account,errors);
        return "Admin_view/register";
    }

    public void checkErrorFields(Account account, Model model) {

        if (accountService.isEmailExists(account.getEmail())) {
            model.addAttribute("emailExists", "This email already exists");
        }

        if (!RegisterValidation.validName(account.getUser().getName())) {
            model.addAttribute("notValidName", "not valid name");

        }

        if (!RegisterValidation.validEmail(account.getEmail())) {
            model.addAttribute("notValidEmail", "not valid email");
        }

        if (!RegisterValidation.validPhoneNo(account.getUser().getPhoneNumber())) {
            model.addAttribute("notValidPhone", "not valid phone number");
        }

        if (!RegisterValidation.validPassword(account.getPassword())) {
            model.addAttribute("notValidPass", "Not valid password,must be at least 8 characters and digits and lower case letters");
        }
    }


}
