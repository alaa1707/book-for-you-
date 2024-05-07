package com.example.bookStore.validation;

import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.Offer;

public class OfferValidation {

    public static boolean checkAllFields(Offer offer) {
        return isValidDiscount(offer.getDiscount()) && isValidBookId(offer.getBook().getId());
    }

    public static boolean isValidDiscount(double discount) {
        return discount > 0;
    }

    public static boolean isValidBookId(int id) {
        return id >=1;
    }
}
