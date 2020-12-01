package com.global.auth.kafka.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.global.auth.kafka.consumer.UserCreationEventConsumer;


@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-creation-consumer", name = "enabled", havingValue = "true")
public class UserCreationConsumerConfiguration {

	@Autowired
	private UserCreationConsumerProperties properties;

	@Bean(destroyMethod = "close")
	public UserCreationEventConsumer userCreationEventConsumer() {
		return new UserCreationEventConsumer(properties.getConsumerProperties(), properties.getTopic());
	}

}
