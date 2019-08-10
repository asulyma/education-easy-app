package com.global.education.service;

import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.kafka.producer.EducationEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private EducationEventProducer educationEventProducer;

    public void sendEvent(String message) {

        try {
            educationEventProducer.sendEvent(message);
        } catch (Exception e) {
            throw new BadRequestParametersRuntimeException(message);
        }

    }

}
