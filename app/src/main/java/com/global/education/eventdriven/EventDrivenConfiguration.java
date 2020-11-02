package com.global.education.eventdriven;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.education.common.dto.event.EventType;
import com.global.education.eventdriven.handler.FinishLessonEventHandler;
import com.global.education.eventdriven.handler.StartCourseEventHandler;


@Configuration
public class EventDrivenConfiguration {

	@Autowired
	private InMemoryQueueEventApplier inMemoryQueueEventApplier;

	@Bean
	public EventDrivenWorker eventDrivenWorker() {
		BlockingQueue<QueueEvent> queue = new LinkedBlockingQueue<>();
		EventDrivenWorker eventDrivenWorker = new EventDrivenWorker(queue, inMemoryQueueEventApplier);
		new Thread(eventDrivenWorker).start();
		return eventDrivenWorker;
	}

	@Bean
	public EventProcessor startCourseEventProcessor() {
		return new EventDrivenEventProcessor(EventType.START_COURSE, eventDrivenWorker());
	}

	@Bean
	public EventProcessor finishLessonEventProcessor() {
		return new EventDrivenEventProcessor(EventType.FINISH_LESSON, eventDrivenWorker());
	}

	@Bean
	public StartCourseEventHandler kafkaStartCourseEventHandler() {
		return new StartCourseEventHandler(startCourseEventProcessor());
	}

	@Bean
	public FinishLessonEventHandler kafkaFinishLessonEventHandler() {
		return new FinishLessonEventHandler(finishLessonEventProcessor());
	}

}
