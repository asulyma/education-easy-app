package com.global.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.education.common.model.Progress;
import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.UserDataEntity;


@Component
public class ValidationService {

	public static final String ID_REGEXP = "^[0-9]{1,9}";

	@Autowired
	private UserDataService userDataService;

	public void checkUserOnAllowGetCourse(Long courseId) {
		UserDataEntity user = userDataService.findCurrentUser();

		if (user.getProgressMap().containsKey(courseId)) {
			return;
		}
		throw new NotAllowedRuntimeException("User " + user.getUsername() + " did not start course " + courseId);
	}

	public void checkUserOnFinishLesson(Long courseId, Long lessonId) {
		UserDataEntity user = userDataService.findCurrentUser();
		Progress progress = user.getProgressMap().get(courseId);

		if (progress == null) {
			throw new NotAllowedRuntimeException("User " + user.getUuid() + " didn't start the course " + courseId);
		}

		if (progress.getAlreadyDoneLessons().contains(lessonId)) {
			throw new BadRequestParametersRuntimeException("User " + user.getUuid() + " already finished the lesson " + lessonId);
		}
	}

}
