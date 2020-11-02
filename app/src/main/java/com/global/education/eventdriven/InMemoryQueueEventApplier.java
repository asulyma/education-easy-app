package com.global.education.eventdriven;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.common.dto.event.EventType;
import com.global.education.eventdriven.applier.EventApplier;


@Service
public class InMemoryQueueEventApplier {

	private final Map<EventType, EventApplier> appliers = new EnumMap<>(EventType.class);

	// Will works before @PreConstruct, but can be replaced to @PostConstruct too
	@Autowired
	private void setAppliers(List<EventApplier> autowiredAppliers) {
		if (autowiredAppliers.size() != EventType.values().length) {
			throw new IllegalStateException("Amount of handlers don't match amount of event types");
		}
		autowiredAppliers.forEach(h -> appliers.put(h.getEventType(), h));
	}

	public void applyEvent(QueueEvent event) {
		EventApplier applier = appliers.get(event.getType());
		applier.doMainWork(event);
	}
}
