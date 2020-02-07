package com.global.education.eventdriven;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventDrivenEventProcessor implements EventProcessor {

    private final EventType eventType;
    private final EventDrivenWorker eventDrivenWorker;

    @Override
    public void process(Object o) {
        // save to DB
        // em.save(o);
        eventDrivenWorker.sendToQueue(new QueueEvent(o, eventType));

    }
}
