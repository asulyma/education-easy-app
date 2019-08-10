package com.global.education.kafka.producer.config;

import com.global.education.kafka.producer.EducationEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.education-event", name = "enabled", havingValue = "true")
public class EducationEventConfiguration {

    @Autowired
    private EducationEventProperties properties;

    @Bean(destroyMethod = "close")
    public EducationEventProducer educationEventProducer() {
        return new EducationEventProducer(properties.getProducerProperties(), properties.getTopic());
    }

}
