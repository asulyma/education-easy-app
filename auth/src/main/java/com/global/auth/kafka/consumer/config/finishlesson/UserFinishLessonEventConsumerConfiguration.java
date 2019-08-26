package com.global.auth.kafka.consumer.config.finishlesson;

import com.global.auth.kafka.consumer.UserFinishLessonEventConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-finish-lesson-event", name = "enabled", havingValue = "true")
public class UserFinishLessonEventConsumerConfiguration {

    @Autowired
    private UserFinishLessonEventConsumerProperties properties;

    @Bean(destroyMethod = "close")
    public UserFinishLessonEventConsumer finishLessonEventConsumer() {
        return new UserFinishLessonEventConsumer(properties.getConsumerProperties(), properties.getTopic());
    }

}
