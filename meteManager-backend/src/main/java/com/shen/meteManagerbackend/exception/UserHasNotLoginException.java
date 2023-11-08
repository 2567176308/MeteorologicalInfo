package com.shen.meteManagerbackend.exception;

public class UserHasNotLoginException extends RuntimeException{
    public UserHasNotLoginException(String message) {
        super(message);
    }
}
