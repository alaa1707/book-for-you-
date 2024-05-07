package com.example.bookStore.controller;


import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.service.UserService;
import com.example.bookStore.validation.BookValidation;
import com.example.bookStore.validation.RegisterValidation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bookStore")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/showAccountDetailsPage")
    public String showAccountDetailsPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "Customer_view/account-details";
    }

    @GetMapping("changeAccountData")
    public String changeAccountData(@ModelAttribute User user, HttpSession session,Model model) {
        User savedUser = (User) session.getAttribute("user");
        if (user.getName() != savedUser.getName() || user.getPhoneNumber() != savedUser.getPhoneNumber()) {
            if (RegisterValidation.validName(user.getName()) && RegisterValidation.validPhoneNo(user.getPhoneNumber())) {
                savedUser.setName(user.getName());
                savedUser.setPhoneNumber(user.getPhoneNumber());
                session.setAttribute("user", savedUser); // Update user data in session
                userService.saveUser(savedUser);
            }
            else{
                checkErrorFields(user,model);
            }
        }
        return "redirect:/bookStore/showAccountDetailsPage";
    }

    public void checkErrorFields(User user, Model model) {

        if (!RegisterValidation.validName(user.getName())) {
            model.addAttribute("notValidName", "not valid name");

        }

        if (!RegisterValidation.validPhoneNo(user.getPhoneNumber())) {
            model.addAttribute("notValidPhone", "not valid phone number");
        }


    }

}
