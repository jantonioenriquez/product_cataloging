package com.cataloging.user.service.messaging.publisher.pubsub;

import com.cataloging.pubsub.producer.service.PubsubProducer;
import com.cataloging.pubsub.user.avro.model.UserAvroModel;
import com.cataloging.user.service.domain.config.UserServiceConfigData;
import com.cataloging.user.service.domain.ports.message.publisher.UserMessagePublisher;
import com.cataloging.user.service.domain.core.event.UserCreatedEvent;
import com.cataloging.user.service.messaging.mapper.UserMessagingDataMapper;
import com.google.api.core.ApiFutureCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Message publisher class to pubsub in user creation event
 */
@Slf4j
@Component
public class UserCreatedEventPubsubPublisher implements UserMessagePublisher {
    private final UserMessagingDataMapper userMessagingDataMapper;
    private final PubsubProducer<String, UserAvroModel> pubsubProducer;
    private final UserServiceConfigData userServiceConfigData;

    public UserCreatedEventPubsubPublisher(UserMessagingDataMapper userMessagingDataMapper,
                                           PubsubProducer<String, UserAvroModel> pubsubProducer,
                                           UserServiceConfigData userServiceConfigData) {
        this.userMessagingDataMapper = userMessagingDataMapper;
        this.pubsubProducer = pubsubProducer;
        this.userServiceConfigData = userServiceConfigData;
    }

    /**
     * Method for publish messages with user data
     * @param userCreatedEvent contains user attributes
     */
    @Override
    public void publish(UserCreatedEvent userCreatedEvent) {
        log.debug("Received UserCreatedEvent for user id: {}",
                userCreatedEvent.getUser().getId().getValue());
        try {
            UserAvroModel userAvroModel = userMessagingDataMapper
                    .userCreateEventoTouserAvroModel(userCreatedEvent);

            pubsubProducer.send(userServiceConfigData.getUserCreateTopicName(),
                    userAvroModel.getEmail(),
                    userAvroModel,
                    getCallback(userServiceConfigData.getUserCreateTopicName(), userAvroModel));

            log.debug("UserCreatedEvent sent to pubsub for user id: {}",
                    userAvroModel.getId());
        } catch (Exception e) {
            log.error("Error while sending UserCreatedEvent to pubsub for user id: {}," +
                    " error: {}", userCreatedEvent.getUser().getId().getValue(), e.getMessage());
        }
    }

    private ApiFutureCallback<String> getCallback(String topicName, UserAvroModel message) {
        return new ApiFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(String result) {
                log.debug("Received new metadata,{}",result);

            }
        };
    }
}
