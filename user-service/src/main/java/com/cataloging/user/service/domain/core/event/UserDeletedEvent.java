package com.cataloging.user.service.domain.core.event;

import com.cataloging.event.DomainEvent;
import com.cataloging.user.service.domain.core.entity.User;

import java.time.ZonedDateTime;

public class UserDeletedEvent implements DomainEvent<User> {
    private final User user;
    private String status;
    private String message;
    private final ZonedDateTime deletedAt;

    public UserDeletedEvent(User user, String status, ZonedDateTime deletedAt) {
        this.user = user;
        this.deletedAt = deletedAt;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}