package com.global.education.eventdriven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class EventDrivenConfiguration {

    @Autowired
    private InMemoryQueueEventProcessor inMemoryQueueEventProcessor;

    @Bean
    public EventDrivenWorker eventDrivenWorker() {
        BlockingQueue<QueueEvent> queue = new LinkedBlockingQueue<>();
        return new EventDrivenWorker(queue, inMemoryQueueEventProcessor);
    }

    @Bean
    public EventProcessor eventOneProcessor() {
        return new EventDrivenEventProcessor(EventType.EVENT_ONE, eventDrivenWorker());
    }

    @Bean
    public EventProcessor eventTwoProcessor() {
        return new EventDrivenEventProcessor(EventType.EVENT_TWO, eventDrivenWorker());
    }

    @Bean
    public KafkaEventOneHandler eventOneHandler() {
        return new KafkaEventOneHandler(eventOneProcessor());
    }

    @Bean
    public KafkaEventTwoHandler eventTwoHandler() {
        return new KafkaEventTwoHandler(eventTwoProcessor());
    }

    // -----------------------------------------------------------------------------------------------------------------
}
