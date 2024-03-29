package com.global.education.eventdriven;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class EventDrivenWorker implements Runnable {

    private final BlockingQueue<QueueEvent> queue;
    private final InMemoryQueueEventApplier applier;

    public void sendToQueue(QueueEvent event) {
        queue.add(event);
    }

    @SneakyThrows
    @Override
    public void run() {
        QueueEvent event;
        while ((event = queue.take()) != null) {
            applier.applyEvent(event);
        }
    }
}
