package com.example.bookStore.service;

import com.example.bookStore.model.entities.Email;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EmailNotificationService {
    private final WebClient webClient;

    public EmailNotificationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://email-microservice:9081").build();
    }

    public void sendEmail(Email email) {
        webClient.post()
                .uri("/api/notifications/email")
                .body(Mono.just(email), Email.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}

