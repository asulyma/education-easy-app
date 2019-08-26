package com.global.auth.kafka.consumer;

import com.education.common.kafka.dto.UserStartCourseEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

import static com.education.common.utils.JacksonUtils.toObject;

@Slf4j
public class UserStartCourseEventConsumer extends EventConsumer {

    public UserStartCourseEventConsumer(Map<String, Object> configs, String topic) {
        super(configs, topic);
    }

    @SuppressWarnings("unchecked")
    @KafkaListener(topics = "education-start-course-event", groupId = "user-start-course-event-group")
    public void listen(Object message) {
        if (validate(message)) {
            log.warn("Empty message for user-start-course-event-group");
            return;
        }

        ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) message;
        userService.startCourse(toObject(record.value(), UserStartCourseEvent.class));
        log.info("Got event from {} topic with start course event", topic);
    }

}
