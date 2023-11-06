package com.shen.meteManagerbackend.exception;

import java.sql.SQLException;

public class DuplicateRegistrationException extends SQLException {


    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
