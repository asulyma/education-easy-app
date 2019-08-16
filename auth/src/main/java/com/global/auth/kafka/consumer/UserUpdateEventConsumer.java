package com.global.auth.kafka.consumer;

import com.global.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class UserUpdateEventConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "education-topic-test", groupId = "user-update-group")
    public void listen(Object message) {
        if (StringUtils.isEmpty(message) || !(message instanceof UserUpdateEventDto)) {
            log.warn("Empty message for user-update-event-consumer");
            return;
        }
        UserUpdateEventDto dto = (UserUpdateEventDto) message;
        userService.addAllowedCourse(dto.getUserId(), dto.getCourseId());
    }

}
