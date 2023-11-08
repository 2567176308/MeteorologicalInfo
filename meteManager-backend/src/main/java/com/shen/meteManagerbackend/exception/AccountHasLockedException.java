package com.shen.meteManagerbackend.exception;

public class AccountHasLockedException extends RuntimeException {
    public AccountHasLockedException(String message) {
        super(message);
    }
}
