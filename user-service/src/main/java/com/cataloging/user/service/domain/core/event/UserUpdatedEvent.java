package com.cataloging.user.service.domain.core.event;

import com.cataloging.event.DomainEvent;
import com.cataloging.user.service.domain.core.entity.User;
import lombok.Getter;

import java.time.ZonedDateTime;

public class UserUpdatedEvent implements DomainEvent<User> {

    @Getter
    private User user;
    private String status;
    private String message;
    private final ZonedDateTime createdAt;

    public UserUpdatedEvent(User user, ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        this.user = user;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
