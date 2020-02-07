package com.global.education.eventdriven;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventOneHandler {

    private final EventProcessor processor;

    public void handle(Object event) {
        processor.process(event);
    }

}
