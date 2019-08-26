package com.global.auth.kafka.consumer.config.startcourse;

import com.global.auth.kafka.consumer.UserStartCourseEventConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-start-course-event", name = "enabled", havingValue = "true")
public class UserStartCourseEventConsumerConfiguration {

    @Autowired
    private UserStartCourseEventConsumerProperties properties;

    @Bean(destroyMethod = "close")
    public UserStartCourseEventConsumer startCourseEventConsumer() {
        return new UserStartCourseEventConsumer(properties.getConsumerProperties(), properties.getTopic());
    }

}
