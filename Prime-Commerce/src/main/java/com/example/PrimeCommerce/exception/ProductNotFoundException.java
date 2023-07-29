package com.example.PrimeCommerce.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String str) {
        super(str);
    }
}
