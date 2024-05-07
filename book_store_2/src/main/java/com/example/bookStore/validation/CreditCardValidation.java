package com.example.bookStore.validation;

import com.example.bookStore.model.entities.PaymentDetails;

import java.util.regex.Pattern;

public class CreditCardValidation {



    private static final String CARD_NUMBER_PATTERN = "^([0-9]{16})$";
    private static final String EXPIRATION_DATE_PATTERN = "^([1-9]|1[0-2])/(\\d{4})$";
    private static final String CVV_PATTERN = "^\\d{3,4}$";
    private static final String CARD_HOLDER_NAME_PATTERN = "^([A-Za-z\s]){3,}+$";


    public static boolean isValidCreditCardNumber(String cardNum) {
        return !cardNum.trim().isEmpty() && cardNum.matches(CARD_NUMBER_PATTERN);
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        return expirationDate != null && expirationDate.matches(EXPIRATION_DATE_PATTERN);
    }

    public static boolean isValidCVV(String cvv) {
        return cvv != null && cvv.matches(CVV_PATTERN);
    }

    public static boolean isValidCardHolderName(String cardHolderName) {
        return cardHolderName != null && cardHolderName.matches(CARD_HOLDER_NAME_PATTERN);
    }

    public static boolean checkAllFields(PaymentDetails paymentDetails){
        return isValidCreditCardNumber(paymentDetails.getCardNumber()) && isValidCVV(paymentDetails.getCvv())
                && isValidCardHolderName(paymentDetails.getCardHolderName()) && isValidExpirationDate(paymentDetails.getExpirationDate());
    }
}
