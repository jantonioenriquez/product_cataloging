package com.cataloging.user.service.domain.ports.message.publisher;


import com.cataloging.user.service.domain.core.event.UserCreatedEvent;

public interface UserMessagePublisher {
    void publish(UserCreatedEvent userCreatedEvent);
}