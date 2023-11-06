package com.shen.meteManagerbackend.exception;

public class AccountHasLockedException extends Exception {
    public AccountHasLockedException(String message) {
        super(message);
    }
}
