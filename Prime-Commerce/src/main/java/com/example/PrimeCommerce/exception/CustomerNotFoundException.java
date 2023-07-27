package com.example.PrimeCommerce.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
