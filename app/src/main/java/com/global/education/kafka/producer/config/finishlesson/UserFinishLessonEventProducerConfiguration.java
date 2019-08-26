package com.global.education.kafka.producer.config.finishlesson;

import com.global.education.kafka.producer.UserFinishLessonEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-finish-lesson-event", name = "enabled", havingValue = "true")
public class UserFinishLessonEventProducerConfiguration {

    @Autowired
    private UserFinishLessonEventProducerProperties properties;

    @Bean(destroyMethod = "close")
    public UserFinishLessonEventProducer finishLessonEventProducer() {
        return new UserFinishLessonEventProducer(properties.getProducerProperties(), properties.getTopic());
    }

}
