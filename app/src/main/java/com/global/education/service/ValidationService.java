package com.global.education.service;

import static java.lang.String.format;

import org.springframework.stereotype.Component;

import com.education.common.model.Progress;
import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.UserDataEntity;
import com.global.education.model.learning.LessonEntity;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class ValidationService {

	public static final String ID_REGEXP = "^[0-9]{1,9}";
	private static final String USER_START_COURSE = "User %s did not start the course %s";
	private static final String USER_FINISHED_LESSON = "User %s already finished the lesson %s";

	private final UserDataService userDataService;
	private final LessonService lessonService;

	public void checkUserOnAllowGetCourse(Long courseId) {
		UserDataEntity user = userDataService.findCurrentUser();

		if (user.getProgressMap().containsKey(courseId)) {
			return;
		}
		throw new NotAllowedRuntimeException(format(USER_START_COURSE, user.getUsername(), courseId));
	}

	public void checkUserOnFinishLesson(Long courseId, Long lessonId) {
		UserDataEntity user = userDataService.findCurrentUser();
		Progress progress = user.getProgressMap().get(courseId);

		if (progress == null) {
			throw new NotAllowedRuntimeException(format(USER_START_COURSE, user.getUsername(), courseId));
		}

		if (progress.getAlreadyDoneLessons().contains(lessonId)) {
			throw new BadRequestParametersRuntimeException(format(USER_FINISHED_LESSON, user.getUuid(), lessonId));
		}
	}

	public void checkUserOnAllowGetComment(Long lessonId) {
		LessonEntity lesson = lessonService.getLessonById(lessonId);
		Long courseId = lesson.getCourse().getId();

		checkUserOnAllowGetCourse(courseId);
	}

}
