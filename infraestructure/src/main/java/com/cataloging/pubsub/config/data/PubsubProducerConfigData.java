package com.cataloging.pubsub.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "pubsub.producer")
public class PubsubProducerConfigData {
    private String configY;
}
