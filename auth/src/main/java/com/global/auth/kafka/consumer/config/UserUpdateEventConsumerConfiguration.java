package com.global.auth.kafka.consumer.config;

import com.global.auth.kafka.consumer.UserUpdateEventConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-update-event-consumer", name = "enabled", havingValue = "true")
public class UserUpdateEventConsumerConfiguration {

    @Autowired
    private UserUpdateEventConsumerProperties properties;

    @Bean(destroyMethod = "close")
    public UserUpdateEventConsumer educationEventConsumer() {
        return new UserUpdateEventConsumer(properties.getConsumerProperties());
    }

}
