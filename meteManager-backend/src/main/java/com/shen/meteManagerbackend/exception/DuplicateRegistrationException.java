package com.shen.meteManagerbackend.exception;

import java.sql.SQLException;

public class DuplicateRegistrationException extends RuntimeException {


    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
