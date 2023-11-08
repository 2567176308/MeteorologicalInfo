package com.shen.meteManagerbackend.exception;

public class PasswordOrEmailErrorException extends RuntimeException {
    public PasswordOrEmailErrorException(String message) {
        super(message);
    }
}
