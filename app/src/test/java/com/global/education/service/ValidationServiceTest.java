package com.global.education.service;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.education.common.model.Progress;
import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.model.UserDataEntity;


@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

	private static final long TEST_COURSE_ID = 1L;
	private static final long TEST_LESSON_ID = 2L;

	@InjectMocks
	private ValidationService testInstance;
	@Mock
	private UserDataService userDataService;

	private UserDataEntity userDataEntity;

	@Before
	public void init() {
		userDataEntity = new UserDataEntity();
		when(userDataService.findCurrentUser()).thenReturn(userDataEntity);
	}

	@Test(expected = NotAllowedRuntimeException.class)
	public void shouldThrowExceptionWhenUserDidntStartCourse() {
		testInstance.checkUserOnAllowGetCourse(TEST_COURSE_ID);
	}

	@Test
	public void shouldNotThrowExceptionWhenUserStartCourse() {
		userDataEntity.getProgressMap().put(TEST_COURSE_ID, new Progress());
		testInstance.checkUserOnAllowGetCourse(TEST_COURSE_ID);
	}

	@Test(expected = NotAllowedRuntimeException.class)
	public void shouldThrowExceptionWhenUserDidntStartCourseForFinishLesson() {
		testInstance.checkUserOnFinishLesson(TEST_COURSE_ID, TEST_LESSON_ID);
	}

	@Test(expected = BadRequestParametersRuntimeException.class)
	public void shouldThrowExceptionWhenUserAlreadyFinishedLesson() {
		Progress progress = new Progress();
		progress.getAlreadyDoneLessons().add(TEST_LESSON_ID);
		userDataEntity.getProgressMap().put(TEST_COURSE_ID, progress);

		testInstance.checkUserOnFinishLesson(TEST_COURSE_ID, TEST_LESSON_ID);
	}

	@Test
	public void shouldNotThrowExceptionWhenUserFinishLesson() {
		Progress progress = new Progress();
		progress.setAlreadyDoneLessons(Collections.emptyList());
		userDataEntity.getProgressMap().put(TEST_COURSE_ID, progress);

		testInstance.checkUserOnFinishLesson(TEST_COURSE_ID, TEST_LESSON_ID);
	}

}
