package com.example.bookStore.controller;

import com.example.bookStore.model.entities.Email;
import com.example.bookStore.model.entities.Contact;
import com.example.bookStore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("bookStore")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private EmailNotificationService emailService;

    @GetMapping("/contacts")
    public String showAllContactsPage(Model model, Model replyModel) {
        List<Contact> contacts = contactService.getAllContactsAfterSetEmails();
        model.addAttribute("contacts", contacts);
        replyModel.addAttribute("email", new Email());
        return "Admin_view/Contact";
    }

    @GetMapping("/reply/{customerEmail}/{contactId}")
    public String setReply(@ModelAttribute("email") Email email, @PathVariable("customerEmail") String customerEmail, @PathVariable("contactId") int contactId) {
        email.setTo(customerEmail);
        email.setSubject("Reply to your contact message from our bookStore admin");
        emailService.sendEmail(email);
        contactService.deleteContact(contactId);
        return "redirect:/bookStore/contacts";
    }


}
