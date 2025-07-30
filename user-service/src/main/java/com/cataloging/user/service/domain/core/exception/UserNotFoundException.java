package com.cataloging.user.service.domain.core.exception;


import com.cataloging.exception.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
