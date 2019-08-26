package com.global.auth.kafka.consumer;

import com.global.auth.service.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

public abstract class EventConsumer extends KafkaConsumer<String, String> {

    @Autowired
    protected UserService userService;

    protected final String topic;

    public EventConsumer(Map<String, Object> configs, String topic) {
        super(configs);
        this.topic = topic;
    }

    protected abstract void listen(Object message);

    protected boolean validate(Object message) {
        return Objects.isNull(message) || !(message instanceof ConsumerRecord);
    }

}
