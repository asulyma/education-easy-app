package com.global.auth.kafka.consumer;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

import static com.education.common.utils.JacksonUtils.toObject;

@Slf4j
public class UserFinishLessonEventConsumer extends EventConsumer {

    public UserFinishLessonEventConsumer(Map<String, Object> configs, String topic) {
        super(configs, topic);
    }

    @SuppressWarnings("unchecked")
    @KafkaListener(topics = "education-finish-lesson-event", groupId = "user-finish-lesson-event-group")
    public void listen(Object message) {
        if (validate(message)) {
            log.warn("Empty message for user-finish-lesson-event-group");
            return;
        }

        ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) message;
        userService.finishLesson(toObject(record.value(), UserFinishLessonEvent.class));
        log.info("Got event from {} topic with finish lesson event", topic);
    }

}
