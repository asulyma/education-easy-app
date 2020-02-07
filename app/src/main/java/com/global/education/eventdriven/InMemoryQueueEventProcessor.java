package com.global.education.eventdriven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemoryQueueEventProcessor {

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
