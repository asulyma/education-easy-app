package com.global.education.kafka.consumer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.education-event-consumer", name = "enabled", havingValue = "true")
public class EducationEventConsumerConfiguration {

}
