package com.global.education.IT;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.education.common.model.Progress;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.controller.handler.exception.NotRegisteredRuntimeException;
import com.global.education.model.UserDataEntity;
import com.global.education.model.learning.CourseEntity;
import com.global.education.model.learning.LessonEntity;
import com.global.education.service.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDataServiceIT extends EducationApplicationIT {

	private static final String TEST_VALUE = "value";
	private static final String TRAINEE = "TRAINEE";
	private static final String JAVA_CORE = "Java Core";

	@Autowired
	private UserDataService testInstance;
	@Autowired
	private CourseService courseService;
	@Autowired
	private LessonService lessonService;

	@Test(expected = NotAllowedRuntimeException.class)
	public void shouldThrowExceptionWhenUuidIsNull() {
		testInstance.findUser(null);
	}

	@Test(expected = NotRegisteredRuntimeException.class)
	public void shouldThrowExceptionWhenUserNotFound() {
		testInstance.findUser(UUID.randomUUID());
	}

	@Test
	@WithMockOAuth2User
	public void shouldRegisterUserStartCourseAndFinishLessonCorrectly() {
		// REGISTRATION
		testInstance.createOrUpdateUserData(buildUser());

		// PREPARE TESTING
		List<UserDataEntity> allUsers = testInstance.findAllUsers();
		assertEquals(1, allUsers.size());
		assertTrue(allUsers.get(0).getProgressMap().isEmpty());

		// SEND KAFKA EVENT
		Long courseId = getCourseId();
		courseService.startCourse(courseId);

		// START COURSE TESTING
		await().atMost(5, TimeUnit.SECONDS).until(() -> checkThatProgressMapIsNotEmpty(courseId));

		// PREPARE LESSON TESTING
		List<LessonEntity> lessons = lessonService.getLessons(courseId);
		assertFalse(lessons.isEmpty());
		Long lessonId = lessons.get(0).getId();

		// SEND KAFKA EVENT TO FINISH LESSON
		ResponseEntity<String> response = lessonService.finishLesson(lessonId, courseId);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// FINISH LESSON TESTING
		await().atMost(5, TimeUnit.SECONDS).until(() -> checkThatProgressMapIsNotEmpty(courseId));

		Progress progress = getProgressMap().get(courseId);
		assertNotEquals(0, progress.getProgressValue());
		assertTrue(progress.getAlreadyDoneLessons().contains(lessonId));
		assertEquals(1, progress.getAlreadyDoneLessons().size());
	}

	private boolean checkThatProgressMapIsNotEmpty(Long courseId) {
		Map<Long, Progress> progressMap = getProgressMap();
		return !progressMap.isEmpty() && progressMap.containsKey(courseId);
	}

	private Map<Long, Progress> getProgressMap() {
		List<UserDataEntity> allUsers = testInstance.findAllUsers();
		assertEquals(1, allUsers.size());

		return allUsers.get(0).getProgressMap();
	}

	private Long getCourseId() {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle(JAVA_CORE);

		List<CourseEntity> courses = courseService.findCourses(request);
		assertEquals(1, courses.size());

		return courses.get(0).getId();
	}

	private User buildUser() {
		User dto = new User();
		dto.setEmail(TEST_VALUE);
		dto.setRank(TRAINEE);
		return dto;
	}

}
