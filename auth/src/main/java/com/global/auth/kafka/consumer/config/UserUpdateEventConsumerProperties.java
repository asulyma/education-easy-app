package com.global.auth.kafka.consumer.config;

import com.global.auth.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.user-update-event-consumer")
public class UserUpdateEventConsumerProperties extends KafkaProperties {

}
