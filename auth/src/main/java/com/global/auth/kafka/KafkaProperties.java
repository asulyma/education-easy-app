package com.global.auth.kafka;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class KafkaProperties {

    private String topic;

    private Map<String, Object> producerProperties;

    private Map<String, Object> consumerProperties;

}
