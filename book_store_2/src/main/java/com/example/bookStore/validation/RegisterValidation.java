package com.example.bookStore.validation;

import com.example.bookStore.model.entities.Account;

import java.util.regex.Pattern;

public class RegisterValidation {

    private static final String NAME_PATTERN = "^[A-Za-z\s]+$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_PATTERN =  "[0-9]{11}";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";

    public static boolean validName(String name) {
        return !name.trim().isEmpty() && name.matches(NAME_PATTERN);
    }


    public static boolean validEmail(String email) {
        return !email.trim().isEmpty() && email.matches(EMAIL_PATTERN);
    }

    public static boolean validPhoneNo(String phone) {
        return !phone.trim().isEmpty() && phone.matches(PHONE_PATTERN);
    }

    public static boolean validPassword(String pass) {
        return !pass.trim().isEmpty() && pass.matches(PASSWORD_PATTERN);
    }


    public static boolean checkAllFields(Account account) {
        return validName(account.getUser().getName())  && validPhoneNo(String.valueOf(account.getUser().getPhoneNumber())) && validEmail(account.getEmail()) && validPassword(account.getPassword());
    }
}
