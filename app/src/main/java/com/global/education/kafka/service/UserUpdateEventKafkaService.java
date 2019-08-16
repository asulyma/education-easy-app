package com.global.education.kafka.service;

import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.kafka.producer.UserUpdateEventDto;
import com.global.education.kafka.producer.UserUpdateEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateEventKafkaService {

    @Autowired
    private UserUpdateEventProducer userUpdateEventProducer;

    public void sendUpdateEvent(UserUpdateEventDto dto) {

        try {
            userUpdateEventProducer.sendEvent(dto);
        } catch (Exception e) {
            throw new BadRequestParametersRuntimeException();
        }

    }

}
