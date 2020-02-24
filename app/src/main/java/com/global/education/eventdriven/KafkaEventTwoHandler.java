package com.global.education.eventdriven;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaEventTwoHandler {

    private final EventProcessor processor;

    public void handle(Object event) {
        processor.process(event);
    }

}