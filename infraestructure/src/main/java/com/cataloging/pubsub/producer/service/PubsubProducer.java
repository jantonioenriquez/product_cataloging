package com.cataloging.pubsub.producer.service;

import com.google.api.core.ApiFutureCallback;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;

public interface PubsubProducer<K extends Serializable, V extends SpecificRecordBase> {
    void send(String topicName, K key, V message, ApiFutureCallback<String> callback) throws InterruptedException;
}
