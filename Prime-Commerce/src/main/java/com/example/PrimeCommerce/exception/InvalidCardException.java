package com.example.PrimeCommerce.exception;

public class InvalidCardException extends RuntimeException{
    public InvalidCardException(String s) {
        super(s);
    }
}
