package com.global.education.eventdriven.applier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.education.common.dto.event.EventType;
import com.education.common.dto.event.UserToCourseEvent;
import com.global.education.eventdriven.QueueEvent;
import com.global.education.service.UserDataService;


@Component
public class FinishCourseEventApplier implements EventApplier {

	@Autowired
	private UserDataService userDataService;

	@Override
	public EventType getEventType() {
		return EventType.FINISH_COURSE;
	}

	@Override
	public void doMainWork(QueueEvent event) {
		if (event.getEventDto() instanceof UserToCourseEvent) {
			userDataService.finishCourse((UserToCourseEvent) event.getEventDto());
		}
	}
}
