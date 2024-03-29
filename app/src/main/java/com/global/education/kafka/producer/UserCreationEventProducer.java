package com.global.education.kafka.producer;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserCreationEventProducer extends EventProducer {

    public UserCreationEventProducer(Map<String, Object> configs, String topic) {
        super(configs, topic);
    }
}
