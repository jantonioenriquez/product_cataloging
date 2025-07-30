package com.cataloging.pubsub.producer;

import com.cataloging.pubsub.config.data.PubsubConfigData;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import com.google.pubsub.v1.TopicName;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

@Slf4j
@Configuration
public class PubsubProducerConfig {
    private final PubsubConfigData pubsubConfigData;
    private GoogleCredentials credentials = null;



    public PubsubProducerConfig(PubsubConfigData pubsubConfigData) {
        this.pubsubConfigData = pubsubConfigData;
    }

    public Publisher producerFactory(String topicName) throws Exception {
        TopicName topic = TopicName.of(pubsubConfigData.getProjectId(), topicName);

        if(credentials == null) {
            byte[] decodedBytes = Base64.getDecoder().decode(pubsubConfigData.getConfigFile().getBytes());
            InputStream inputStream = new ByteArrayInputStream(decodedBytes);
            credentials = GoogleCredentials.fromStream(inputStream);
        }
        return Publisher.newBuilder(topic)
                .setEnableMessageOrdering("true".equalsIgnoreCase(pubsubConfigData.getEnableMessageOrdering()))
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();

    }

}
