package com.global.auth.kafka.consumer.config.startcourse;

import com.education.common.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.user-start-course-event")
public class UserStartCourseEventConsumerProperties extends KafkaProperties {

}
