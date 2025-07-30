package com.cataloging.user.service.domain.ports.message.publisher;


import com.cataloging.user.service.domain.core.event.UserDeletedEvent;

public interface UserMessageDeletePublisher {
    void publish(UserDeletedEvent userDeletedEvent);
}
