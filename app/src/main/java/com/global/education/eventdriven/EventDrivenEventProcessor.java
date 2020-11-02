package com.global.education.eventdriven;

import com.education.common.dto.event.EventType;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EventDrivenEventProcessor implements EventProcessor {

	private final EventType eventType;
	private final EventDrivenWorker eventDrivenWorker;

	@Override
	public void process(Object queueEvent) {
		// save to DB
		// em.save(queueEvent);
		eventDrivenWorker.sendToQueue(new QueueEvent(queueEvent, eventType));
	}
}
