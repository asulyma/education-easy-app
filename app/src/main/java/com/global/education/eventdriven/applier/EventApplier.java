package com.global.education.eventdriven.applier;

import com.education.common.dto.event.EventType;
import com.global.education.eventdriven.QueueEvent;


public interface EventApplier {

    EventType getEventType();

    void doMainWork(QueueEvent event);
}
