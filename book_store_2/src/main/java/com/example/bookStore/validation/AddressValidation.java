package com.example.bookStore.validation;

import com.example.bookStore.model.entities.Address;


public class AddressValidation {
    private static final String COUNTRY_CITY_PATTERN = "^[A-Za-z\s]+$";


    public static boolean isValidAddress(Address address) {
        if (address == null) {
            return false;
        }

        // Validate each field individually
        boolean isValidCountry = isValidAddressField(address.getStreet());
        boolean isValidCity = isValidCountryOrCity(address.getCity());
        boolean isValidStreet = isValidCountryOrCity(address.getCountry());
        boolean isValidHomeNumber = isValidHomeNumber(address.getHomeNumber());

        // If all fields are valid, return true; otherwise, return false
        return isValidCountry && isValidCity && isValidStreet && isValidHomeNumber;
    }

    public static boolean isValidAddressField(String field) {
        return field != null && !field.trim().isEmpty();
    }

    public static boolean isValidCountryOrCity(String field){
        return isValidAddressField(field) && field.matches(COUNTRY_CITY_PATTERN);
    }





    public static boolean isValidHomeNumber(int homeNumber){
        return homeNumber>0;
    }
}

