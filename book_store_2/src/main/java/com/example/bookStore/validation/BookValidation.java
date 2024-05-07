package com.example.bookStore.validation;

import com.example.bookStore.model.entities.Book;

public class BookValidation {

    private static final String BOOK_AUTHOR_NAME_PATTERN = "^[A-Za-z\s]+$";

    public static boolean checkAllFields(Book book) {
       return isValidBookAuthorName(book.getAuthor()) && isValidPrice(book.getPrice()) && isValidQuantity(book.getQuantity());
    }

    public static boolean isValidBookAuthorName(String cardNum) {
        return !cardNum.trim().isEmpty() && cardNum.matches(BOOK_AUTHOR_NAME_PATTERN);
    }
    public static boolean isValidPrice(int price) {
        return price > 0;
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }


}
