package com.cataloging.user.service.domain.core.exception;


import com.cataloging.exception.DomainException;

public class UserDomainException extends DomainException {
    public UserDomainException(String message) {
        super(message);
    }
}
