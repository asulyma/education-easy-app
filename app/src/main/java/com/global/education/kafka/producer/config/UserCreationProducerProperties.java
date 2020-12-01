package com.global.education.kafka.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.education.common.dto.KafkaProperties;


@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.user-creation-producer")
public class UserCreationProducerProperties extends KafkaProperties {

}
