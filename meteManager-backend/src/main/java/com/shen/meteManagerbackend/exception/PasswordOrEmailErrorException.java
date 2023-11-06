package com.shen.meteManagerbackend.exception;

public class PasswordOrEmailErrorException extends Exception {
    public PasswordOrEmailErrorException(String message) {
        super(message);
    }
}
