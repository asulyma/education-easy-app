package com.global.education.kafka.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.global.education.kafka.producer.UserCreationEventProducer;


@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-creation-producer", name = "enabled", havingValue = "true")
public class UserCreationProducerConfiguration {

	@Autowired
	private UserCreationProducerProperties properties;

	@Bean(destroyMethod = "close")
	public UserCreationEventProducer userCreationEventProducer() {
		return new UserCreationEventProducer(properties.getProducerProperties(), properties.getTopic());
	}

}
