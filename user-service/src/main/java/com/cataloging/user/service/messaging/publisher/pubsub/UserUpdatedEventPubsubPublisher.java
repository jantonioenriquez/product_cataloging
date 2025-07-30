package com.cataloging.user.service.messaging.publisher.pubsub;

import com.cataloging.pubsub.producer.service.PubsubProducer;
import com.cataloging.pubsub.user.avro.model.UserAvroModel;
import com.cataloging.user.service.domain.config.UserServiceConfigData;
import com.cataloging.user.service.domain.ports.message.publisher.UserMessageUpdatePublisher;
import com.cataloging.user.service.domain.core.event.UserUpdatedEvent;
import com.cataloging.user.service.messaging.mapper.UserMessagingDataMapper;
import com.google.api.core.ApiFutureCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Message publisher class to pubsub in user update event
 */
@Slf4j
@Component
public class UserUpdatedEventPubsubPublisher implements UserMessageUpdatePublisher {
    private final UserMessagingDataMapper userMessagingDataMapper;
    private final PubsubProducer<String, UserAvroModel> pubsubProducer;
    private final UserServiceConfigData userServiceConfigData;

    public UserUpdatedEventPubsubPublisher(UserMessagingDataMapper userMessagingDataMapper,
                                           PubsubProducer<String, UserAvroModel> pubsubProducer,
                                           UserServiceConfigData userServiceConfigData) {
        this.userMessagingDataMapper = userMessagingDataMapper;
        this.pubsubProducer = pubsubProducer;
        this.userServiceConfigData = userServiceConfigData;
    }

    /**
     * Method for publish messages with user data
     * @param userUpdatedEvent contains user attributes
     */
    @Override
    public void publish(UserUpdatedEvent userUpdatedEvent) {
        log.debug("Received SellerUpdatedEvent for user name: {}",
                userUpdatedEvent.getUser().getEmail());
        try {
            UserAvroModel userAvroModel = userMessagingDataMapper.userUpdatedEventoTouserAvroModel(userUpdatedEvent);
            pubsubProducer.send(userServiceConfigData.getUserUpdateTopicName(),
                    userAvroModel.getEmail(),
                    userAvroModel,
                    getCallback(userServiceConfigData.getUserUpdateTopicName(), userAvroModel));

            log.debug("SellerUpdatedEvent sent to pubsub for email: {}", userAvroModel.getEmail());
        } catch (Exception e) {
            log.error("Error while sending UserUpdatedEvent to pub(sub for email: {}," +
                    " error: {}", userUpdatedEvent.getUser().getEmail(),e);
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
