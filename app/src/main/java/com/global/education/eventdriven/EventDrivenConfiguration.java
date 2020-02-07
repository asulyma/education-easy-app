package com.global.education.eventdriven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class EventDrivenConfiguration {

    @Autowired
    private EventDrivenWorker eventDrivenWorker;

    @Bean
    public EventProcessor eventOneProcessor() {
        return new EventDrivenEventProcessor(EventType.EVENT_ONE, eventDrivenWorker);
    }

    @Bean
    public EventProcessor eventTwoProcessor() {
        return new EventDrivenEventProcessor(EventType.EVENT_TWO, eventDrivenWorker);
    }


    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    public EventDrivenWorker eventDrivenWorker(InMemoryQueueEventProcessor inMemoryQueueEventProcessor) {
        BlockingQueue<QueueEvent> queue = new LinkedBlockingQueue<>();
        return new EventDrivenWorker(queue, inMemoryQueueEventProcessor);
    }
}
