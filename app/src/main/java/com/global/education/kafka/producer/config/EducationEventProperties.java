package com.global.education.kafka.producer.config;

import com.global.education.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.education-event")
public class EducationEventProperties extends KafkaProperties {

}