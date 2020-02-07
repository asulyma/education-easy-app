package com.global.education.eventdriven;

import org.springframework.stereotype.Component;

@Component
public class EventTwoApplier implements EventApplier {

    @Override
    public EventType getEventType() {
        return EventType.EVENT_TWO;
    }

    @Override
    public void doMainWork(QueueEvent event) {

    }
}
