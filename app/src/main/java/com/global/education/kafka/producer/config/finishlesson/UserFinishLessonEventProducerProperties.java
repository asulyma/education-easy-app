package com.global.education.kafka.producer.config.finishlesson;

import com.education.common.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "kafka-topics.user-finish-lesson-event")
public class UserFinishLessonEventProducerProperties extends KafkaProperties {

}
