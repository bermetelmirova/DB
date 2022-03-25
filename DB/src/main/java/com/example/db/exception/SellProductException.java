package com.example.db.exception;

public class SellProductException extends RuntimeException{
    public SellProductException() {
    }

    public SellProductException(String message) {
        super(message);
    }
}
