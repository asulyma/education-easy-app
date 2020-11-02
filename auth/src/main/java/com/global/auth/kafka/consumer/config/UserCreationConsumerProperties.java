package com.global.auth.kafka.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.education.common.dto.KafkaProperties;


@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.user-creation-consumer")
public class UserCreationConsumerProperties extends KafkaProperties {

}
