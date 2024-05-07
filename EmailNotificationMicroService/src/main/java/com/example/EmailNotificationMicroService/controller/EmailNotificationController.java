package com.example.EmailNotificationMicroService.controller;

import com.example.EmailNotificationMicroService.model.Email;
import com.example.EmailNotificationMicroService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications/email")
public class EmailNotificationController {
   @Autowired
   private EmailService emailService;

    @PostMapping("")
    public ResponseEntity<Void> sendEmail(@RequestBody Email emailNotification) {
        emailService.sendEmail(emailNotification);
        return ResponseEntity.accepted().build();
    }


}

