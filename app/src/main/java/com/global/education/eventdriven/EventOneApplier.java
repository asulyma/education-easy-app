package com.global.education.eventdriven;

import org.springframework.stereotype.Component;

@Component
public class EventOneApplier implements EventApplier {

    @Override
    public EventType getEventType() {
        return EventType.EVENT_ONE;
    }

    @Override
    public void doMainWork(QueueEvent event) {

    }
}
