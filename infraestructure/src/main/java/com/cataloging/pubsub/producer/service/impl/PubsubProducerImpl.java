package com.cataloging.pubsub.producer.service.impl;

import com.cataloging.pubsub.producer.PubsubProducerConfig;
import com.cataloging.pubsub.producer.exception.PubsubProducerException;
import com.cataloging.pubsub.producer.service.PubsubProducer;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.google.pubsub.v1.PubsubMessage;

@Slf4j
@Component
public class PubsubProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements PubsubProducer<K, V> {
    private final PubsubProducerConfig pubsubProducerConfig;
    private final Map<String, Publisher> publishers;

    public PubsubProducerImpl(PubsubProducerConfig pubsubProducerConfig) {
        this.pubsubProducerConfig = pubsubProducerConfig;
        this.publishers = new HashMap<>();
    }

    @Override
    public void send(String topicName, K key, V message, ApiFutureCallback<String> callback) throws InterruptedException {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            DatumWriter<V> writer = new SpecificDatumWriter<>(message.getSchema());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Encoder encoder = EncoderFactory.get().binaryEncoder(stream, null);
            writer.write(message, encoder);
            encoder.flush();
            byte[] data = stream.toByteArray();
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(ByteString.copyFrom(data))
                    .setOrderingKey(key.toString())
                    .build();
            Publisher publisher = publishers.get(topicName);
            if(publisher == null){
                publisher = pubsubProducerConfig.producerFactory(topicName);
                publishers.put(topicName,publisher);
            }
            ApiFuture<String> apiFuture = publisher.publish(pubsubMessage);
            ApiFutures.addCallback(apiFuture, callback, MoreExecutors.directExecutor());
        } catch (Exception e) {
            log.error("Error on pubsub producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new PubsubProducerException("Error on pubsub producer with key: " + key + " and message: " + message);
        }
    }

    @PreDestroy
    public void shutdown(){
        publishers.forEach((topic,publisher) -> {
            if (publisher != null) {
                log.info("Closing pubsub producer "+topic);
                publisher.shutdown();
                try {
                    publisher.awaitTermination(1, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    log.error("Error closing publisher: "+ topic,e);
                }
            }
        });
    }
}
