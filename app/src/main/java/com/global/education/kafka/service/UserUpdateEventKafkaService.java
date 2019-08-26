package com.global.education.kafka.service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.kafka.producer.UserFinishLessonEventProducer;
import com.global.education.kafka.producer.UserStartCourseEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateEventKafkaService {

    @Autowired
    private UserFinishLessonEventProducer userFinishLessonEventProducer;

    @Autowired
    private UserStartCourseEventProducer userStartCourseEventProducer;

    public void sendFinishLessonEvent(UserFinishLessonEvent dto) {
        try {
            userFinishLessonEventProducer.sendMessage(dto);
        } catch (Exception e) {
            throw new BadRequestParametersRuntimeException();
        }
    }

    public void sendStartCourseEvent(UserStartCourseEvent dto) {
        try {
            userStartCourseEventProducer.sendMessage(dto);
        } catch (Exception e) {
            throw new BadRequestParametersRuntimeException();
        }
    }

}
