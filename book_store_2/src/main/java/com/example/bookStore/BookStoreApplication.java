package com.example.bookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy

public class BookStoreApplication {



	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
