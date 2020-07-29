package com.global.education.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.Progress;
import com.global.education.model.UserDataEntity;
import com.global.education.repository.UserDataRepository;


@RunWith(MockitoJUnitRunner.class)
public class UserDataServiceTest {

	private static final UUID TEST_UUID = UUID.randomUUID();
	private static final long TEST_LESSON_ID = 1L;
	private static final long TEST_COURSE_ID = 1L;
	private static final long TEST_ANOTHER_COURSE_ID = 2L;
	private static final long TEST_COEFFICIENT = 5L;

	@InjectMocks
	private UserDataService testInstance;
	@Mock
	private UserDataRepository userDataRepository;

	private UserDataEntity userDataEntity;
	private Progress progress;

	@Before
	public void init() {
		progress = new Progress();
		progress.setProgressValue(TEST_COEFFICIENT);

		userDataEntity = new UserDataEntity();
		userDataEntity.getProgressMap().put(TEST_COURSE_ID, progress);

		when(userDataRepository.findByUuid(TEST_UUID)).thenReturn(userDataEntity);
	}

	@Test
	public void shouldStartCourseCorrectly() {
		assertEquals(1, userDataEntity.getProgressMap().size());

		UserStartCourseEvent event = new UserStartCourseEvent();
		event.setCourseId(TEST_ANOTHER_COURSE_ID);
		event.setUserUuid(TEST_UUID);

		testInstance.startCourse(event);

		assertEquals(2, userDataEntity.getProgressMap().size());
		assertNotNull(userDataEntity.getProgressMap().get(TEST_ANOTHER_COURSE_ID));
	}

	@Test
	public void shouldFinishLessonCorrectly() {
		assertTrue(progress.getAlreadyDoneLessons().isEmpty());

		UserFinishLessonEvent event = new UserFinishLessonEvent();
		event.setCourseId(TEST_COURSE_ID);
		event.setUserUuid(TEST_UUID);
		event.setAlreadyDoneLesson(TEST_LESSON_ID);
		event.setCoefficientToProgress(TEST_COEFFICIENT);

		testInstance.finishLesson(event);

		assertTrue(userDataEntity.getProgressMap().containsKey(TEST_LESSON_ID));
		assertFalse(progress.getAlreadyDoneLessons().isEmpty());
		assertTrue(progress.getAlreadyDoneLessons().contains(TEST_LESSON_ID));
		assertEquals(TEST_COEFFICIENT + TEST_COEFFICIENT, progress.getProgressValue());
	}
}
