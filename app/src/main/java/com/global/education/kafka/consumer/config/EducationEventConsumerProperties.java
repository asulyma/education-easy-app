package com.global.education.kafka.consumer.config;

import com.global.education.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.education-event-consumer")
public class EducationEventConsumerProperties extends KafkaProperties {

}
