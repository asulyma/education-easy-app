package com.global.auth.kafka.consumer;

import com.global.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
public class UserUpdateEventConsumer extends KafkaConsumer<String, String> {

    @Autowired
    private UserService userService;

    public UserUpdateEventConsumer(Map<String, Object> configs) {
        super(configs);
    }

    @KafkaListener(topics = "education-topic-test", groupId = "user-update-group")
    public void listen(Object message) {
        if (StringUtils.isEmpty(message) || !(message instanceof UserUpdateEventDto)) {
            log.warn("Empty message for user-update-event-consumer");
            return;
        }
        userService.finishLesson((UserUpdateEventDto) message);
    }

}
