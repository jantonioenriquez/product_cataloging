package com.cataloging.pubsub.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "pubsub.consumer")
public class PubsubConsumerConfigData {
    private String ack;
    private String configY;
    private String maxOutstandingElementCount;
}
