package com.global.education.service;

import org.springframework.stereotype.Service;

import com.education.common.dto.event.*;
import com.global.education.aspect.TriggerSendEmail;
import com.global.education.eventdriven.handler.*;
import com.global.education.kafka.producer.UserCreationEventProducer;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@Service
@RequiredArgsConstructor
public class EventService {

	private final UserCreationEventProducer userCreationEventProducer;
	private final StartCourseEventHandler startCourseEventHandler;
	private final FinishLessonEventHandler finishLessonEventHandler;
	private final FinishCourseEventHandler finishCourseEventHandler;

	@TriggerSendEmail(target = EventType.START_COURSE)
	public void sendStartCourseEvent(UserToCourseEvent dto) {
		startCourseEventHandler.handle(dto);
	}

	@TriggerSendEmail(target = EventType.FINISH_LESSON)
	public void sendFinishLessonEvent(UserFinishLessonEvent dto) {
		finishLessonEventHandler.handle(dto);
	}

	@TriggerSendEmail(target = EventType.FINISH_COURSE)
	public void sendFinishCourseEvent(UserToCourseEvent dto) {
		finishCourseEventHandler.handle(dto);
	}

	@SneakyThrows
	public void sendUserCreationEvent(UserCreationEvent dto) {
		userCreationEventProducer.sendMessage(dto);
	}

}
