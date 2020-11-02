package com.global.education.eventdriven.applier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.education.common.dto.event.UserFinishLessonEvent;
import com.education.common.dto.event.EventType;
import com.global.education.eventdriven.QueueEvent;
import com.global.education.service.UserDataService;


@Component
public class FinishLessonEventApplier implements EventApplier {

	@Autowired
	private UserDataService userDataService;

	@Override
	public EventType getEventType() {
		return EventType.FINISH_LESSON;
	}

	@Override
	public void doMainWork(QueueEvent event) {

		if (event.getEventDto() instanceof UserFinishLessonEvent) {
			userDataService.finishLesson((UserFinishLessonEvent) event.getEventDto());
		}
	}
}
