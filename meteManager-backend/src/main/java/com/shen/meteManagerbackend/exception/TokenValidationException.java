package com.shen.meteManagerbackend.exception;

public class TokenValidationException extends RuntimeException{
    public TokenValidationException(String message) {
        super(message);
    }
}
