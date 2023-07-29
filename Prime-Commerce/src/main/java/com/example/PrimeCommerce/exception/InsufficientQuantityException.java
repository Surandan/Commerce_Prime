package com.example.PrimeCommerce.exception;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException(String str) {
        super(str);
    }
}
