package com.global.education.kafka.producer.config.startcourse;

import com.global.education.kafka.producer.UserStartCourseEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "kafka-topics.user-start-course-event", name = "enabled", havingValue = "true")
public class UserStartCourseEventProducerConfiguration {

    @Autowired
    private UserStartCourseEventProducerProperties properties;

    @Bean(destroyMethod = "close")
    public UserStartCourseEventProducer startCourseEventProducer() {
        return new UserStartCourseEventProducer(properties.getProducerProperties(), properties.getTopic());
    }

}
