package com.global.education.eventdriven;

public interface EventApplier {

    EventType getEventType();

    void doMainWork(QueueEvent event);
}
