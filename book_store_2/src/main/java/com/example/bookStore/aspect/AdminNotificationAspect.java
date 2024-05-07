package com.example.bookStore.aspect;

import com.example.bookStore.model.entities.Email;
import com.example.bookStore.model.entities.*;
import com.example.bookStore.service.EmailNotificationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Aspect
@Component
public class AdminNotificationAspect {

    @Autowired
    private EmailNotificationService emailService;

    @AfterReturning(pointcut = "execution(* com.example.bookStore.service.OrderService.createOrder(..))", returning = "order")
    public void notifyAdminOnOrder(JoinPoint joinPoint, Order order) {
        if(InternetConnection.isInternetAvailable()) {

            User user = (User) joinPoint.getArgs()[3];
            String message = createOrderPlacedMsg(user, order);
            sendEmailNotifications(message, "Order Placed Notifications");
        }
    }


    @AfterReturning(pointcut = "execution(* com.example.bookStore.service.RateService.addUserRate(..))")
    public void notifyAdminOnBookRating(JoinPoint joinPoint) {
        if(InternetConnection.isInternetAvailable()) {
            String customerName = ((User) joinPoint.getArgs()[0]).getName();
            String bookName = ((Book) joinPoint.getArgs()[1]).getBookName();
            int numOfStars = ((Rate) joinPoint.getArgs()[2]).getNumOfStars();

            String message = "Customer " + customerName + " has rated the book: " + bookName +
                    ".\nRating: " + numOfStars + " stars";
            sendEmailNotifications(message, "Book is Rated Notifications");
        }
    }

    private String createOrderPlacedMsg(User user, Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Customer ").append(user.getName()).append(" has placed an order:\n");

        for (OrderItem item : orderItems) {
            messageBuilder.append("- ").append(item.getBook().getBookName()).append(" (x").append(item.getQuantity()).append(")\n");
        }

        messageBuilder.append("\nOrder ID: ").append(order.getId());

        String message = messageBuilder.toString();
        return message;
    }

    private void sendEmailNotifications(String message, String subject) {
        Email email = new Email("alaahassan2019th@gmail.com", subject, message);
        emailService.sendEmail(email);
    }


}
