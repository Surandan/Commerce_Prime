package com.example.PrimeCommerce.exception;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String message) {
        super(message);
    }
}
