package com.global.education.kafka.producer;

import com.education.common.utils.JacksonUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class EventProducer extends KafkaProducer<String, String> {

    private static final int TIMEOUT_PERIOD = 30;
    private final String topic;

    public EventProducer(Map<String, Object> configs, String topic) {
        super(configs);
        this.topic = topic;
    }

    public <T> void sendMessage(T message) throws Exception {
        String payload = JacksonUtils.toJsonString(message);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, payload);
        sendMessage(record);
    }

    private void sendMessage(ProducerRecord<String, String> record) throws Exception {
        this.send(record).get(TIMEOUT_PERIOD, TimeUnit.SECONDS);
    }
}
