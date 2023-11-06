package com.shen.meteManagerbackend.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotfoundException extends UsernameNotFoundException {
    public EmailNotfoundException(String message) {
        super(message);
    }
}
