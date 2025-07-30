package com.cataloging.user.service.messaging.mapper;

import com.cataloging.pubsub.user.avro.model.UserAvroModel;
import com.cataloging.user.service.domain.core.event.UserCreatedEvent;
import com.cataloging.user.service.domain.core.event.UserDeletedEvent;
import com.cataloging.user.service.domain.core.event.UserUpdatedEvent;
import org.springframework.stereotype.Component;


/**
 * Class for mapping user attributes to object UserAvroModel for
 * publish message on events create, update and delete
 */
@Component
public class UserMessagingDataMapper {

    /**
     * Method for map attributes from UserCreatedEvent to UserAvroModel
     *
     * @param userCreatedEvent - user attributes
     * @return Object AvroModel for publish message on event create user
     */
    public UserAvroModel userCreateEventoTouserAvroModel(UserCreatedEvent userCreatedEvent) {
        UserAvroModel userAvroModel = new UserAvroModel();
        userAvroModel.setId(userCreatedEvent.getUser().getId().getValue());
        userAvroModel.setEmail(userCreatedEvent.getUser().getEmail());
        userAvroModel.setStatus(userCreatedEvent.getUser().getStatus().toString());
        userAvroModel.setAuth0Id(userCreatedEvent.getUser().getAuth0Id());
        return userAvroModel;
    }

    /**
     * Method for map attributes from UserUpdatedEvent to UserAvroModel
     *
     * @param userUpdatedEvent - user attributes
     * @return Object AvroModel for publish message on event update user
     */
    public UserAvroModel userUpdatedEventoTouserAvroModel(UserUpdatedEvent userUpdatedEvent) {
        UserAvroModel userAvroModel = new UserAvroModel();
        userAvroModel.setId(userUpdatedEvent.getUser().getId().getValue());
        userAvroModel.setEmail(userUpdatedEvent.getUser().getEmail());
        userAvroModel.setStatus(userUpdatedEvent.getUser().getStatus().toString());
        userAvroModel.setAuth0Id(userUpdatedEvent.getUser().getAuth0Id());
        return userAvroModel;
    }

    /**
     * Method for map attributes from UserDeletedEvent to UserAvroModel
     *
     * @param userDeletedEvent - user attributes
     * @return Object AvroModel for publish message on event update user
     */
    public UserAvroModel userDeletedEventoTouserAvroModel(UserDeletedEvent userDeletedEvent) {
        UserAvroModel userAvroModel = new UserAvroModel();
        userAvroModel.setId(userDeletedEvent.getUser().getId().getValue());
        userAvroModel.setEmail(userDeletedEvent.getUser().getEmail());
        userAvroModel.setAuth0Id(userDeletedEvent.getUser().getAuth0Id());
        return userAvroModel;
    }

}