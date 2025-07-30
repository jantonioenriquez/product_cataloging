package com.cataloging.user.service.domain.ports.message.publisher;


import com.cataloging.user.service.domain.core.event.UserUpdatedEvent;

public interface UserMessageUpdatePublisher {
    void publish(UserUpdatedEvent userUpdatedEvent);
}
