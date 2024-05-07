package com.example.bookStore.service;

import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.Rate;
import com.example.bookStore.model.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        for (Book book : books) {
            book.setBookType(getCategoryName(book.getId()));
            bookRepo.save(book);
        }
        return books;
    }

    private String getCategoryName(int bookId) {
        return bookRepo.findCategoryNameByBookId(bookId);
    }

    public void deleteBook(int bookId) {
        bookRepo.deleteById(bookId);
    }

    public void saveBook(Book book) {
        bookRepo.save(book);
    }


    public Optional<Book> getBook(int id) {
        return bookRepo.findById(id);
    }

    public boolean isBookFound(int id) {
        return getBook(id).isPresent();
    }

    public String encodeBookImage(Book book) {
        return Base64.getEncoder().encodeToString(book.getImage());

    }

    public int numOfBooks() {
        return getAllBooks().size();
    }

    public List<Book> topRatesBooks() {
        List<Book> top = new ArrayList<>();
        for (int i = 0; i < getAllBooks().size(); i++) {
            Book book = getAllBooks().get(i);
            for (Rate rate : book.getRates()) {
                if (rate.getNumOfStars() == 5 && !top.contains(book)) {
                    top.add(book);
                }
            }
        }
        return top;
    }


    public List<Book> searchBooks(String keyword) {
        List<Book> allBooks = bookRepo.findAll();
        return allBooks.stream()
                .filter(book -> book.getBookName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}

