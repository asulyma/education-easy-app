package com.global.education.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.education.common.model.Progress;
import com.global.education.model.UserDataEntity;


@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

	private static final long TEST_COURSE_ID = 1L;

	@Mock
	private UserDataService userDataService;
	@Mock
	private EventService kafkaService;
	@InjectMocks
	private CourseService testInstance;

	private final UserDataEntity user = new UserDataEntity();

	@Before
	public void init() {
		when(userDataService.findCurrentUser()).thenReturn(user);
	}

	@Test
	public void shouldSendKafkaEventWhenUserStartCourse() {
		testInstance.startCourse(TEST_COURSE_ID);
		verify(kafkaService).sendStartCourseEvent(any());
	}

	@Test
	public void shouldNotSendKafkaEventWhenUserAlreadyStartedCourse() {
		user.getProgressMap().put(TEST_COURSE_ID, new Progress());
		testInstance.startCourse(TEST_COURSE_ID);
		verify(kafkaService, never()).sendStartCourseEvent(any());
	}
}
