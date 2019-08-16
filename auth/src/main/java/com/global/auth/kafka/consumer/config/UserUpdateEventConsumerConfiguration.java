package com.global.auth.kafka.consumer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-update-event-consumer", name = "enabled", havingValue = "true")
public class UserUpdateEventConsumerConfiguration {

}
