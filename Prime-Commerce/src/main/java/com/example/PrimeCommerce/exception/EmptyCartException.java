package com.example.PrimeCommerce.exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String s) {
        super(s);
    }
}
