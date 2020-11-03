package com.global.education.service;

import org.springframework.stereotype.Service;

import com.education.common.dto.event.*;
import com.global.education.aspect.TriggerSendEmail;
import com.global.education.eventdriven.handler.FinishLessonEventHandler;
import com.global.education.eventdriven.handler.StartCourseEventHandler;
import com.global.education.kafka.producer.UserCreationEventProducer;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@Service
@RequiredArgsConstructor
public class EventService {

	private final UserCreationEventProducer userCreationEventProducer;
	private final StartCourseEventHandler startCourseEventHandler;
	private final FinishLessonEventHandler finishLessonEventHandler;

	@SneakyThrows
	@TriggerSendEmail(target = EventType.START_COURSE)
	public void sendStartCourseEvent(UserStartCourseEvent dto) {
		startCourseEventHandler.handle(dto);
	}

	@SneakyThrows
	@TriggerSendEmail(target = EventType.FINISH_LESSON)
	public void sendFinishLessonEvent(UserFinishLessonEvent dto) {
		finishLessonEventHandler.handle(dto);
	}

	@SneakyThrows
	public void sendUserCreationEvent(UserCreationEvent dto) {
		userCreationEventProducer.sendMessage(dto);
	}

}
