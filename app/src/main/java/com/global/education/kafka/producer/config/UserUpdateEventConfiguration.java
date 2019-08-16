package com.global.education.kafka.producer.config;

import com.global.education.kafka.producer.UserUpdateEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-update-event", name = "enabled", havingValue = "true")
public class UserUpdateEventConfiguration {

    @Autowired
    private UserUpdateEventProperties properties;

    @Bean(destroyMethod = "close")
    public UserUpdateEventProducer educationEventProducer() {
        return new UserUpdateEventProducer(properties.getProducerProperties(), properties.getTopic());
    }

}
