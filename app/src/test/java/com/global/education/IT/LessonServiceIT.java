package com.global.education.IT;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.global.education.controller.dto.SharedLesson;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.kafka.service.UserUpdateEventKafkaService;
import com.global.education.model.learning.CourseEntity;
import com.global.education.model.learning.LessonEntity;
import com.global.education.service.CourseService;
import com.global.education.service.LessonService;


public class LessonServiceIT extends EducationApplicationIT {

	private static final String TEST_VALUE = "value";
	private static final String JAVA_CORE = "Java Core";

	@Autowired
	private LessonService testInstance;
	@Autowired
	private CourseService courseService;

	@MockBean
	private UserUpdateEventKafkaService kafkaService;

	@Before
	public void init() {
		doNothing().when(kafkaService).sendFinishLessonEvent(any());
	}

	@Test
	public void shouldFindLessonsCorrectly() {
		Long courseId = getCourseId();
		Long lessonId = getLessonId(courseId);

		LessonEntity actualLessonById = testInstance.getLessonById(lessonId, courseId);

		assertNotNull(actualLessonById);
	}

	@Test(expected = NotFoundRuntimeException.class)
	public void shouldThrowExceptionWhenLessonDoesNotExist() {
		testInstance.getLessonById(333L, 333L);
	}

	@Test
	public void shouldCreateAndRemoveLessonCorrectly() {
		Long courseId = getCourseId();
		List<LessonEntity> prepare = testInstance.getLessons(courseId);
		assertTrue(prepare.stream().map(LessonEntity::getTitle).noneMatch(e -> e.equals(TEST_VALUE)));

		SharedLesson dto = new SharedLesson();
		dto.setTitle(TEST_VALUE);
		dto.setCourseId(courseId);

		testInstance.createLesson(dto);

		List<LessonEntity> actual = testInstance.getLessons(courseId);
		LessonEntity lesson = actual.stream().filter(e -> e.getTitle().equals(TEST_VALUE)).findFirst().orElse(null);
		assertNotNull(lesson);
		assertNull(lesson.getDescription());
		assertEquals(TEST_VALUE, lesson.getTitle());

		testInstance.removeLesson(lesson.getId());

		List<LessonEntity> after = testInstance.getLessons(courseId);
		assertTrue(after.stream().map(LessonEntity::getTitle).noneMatch(e -> e.equals(TEST_VALUE)));
	}

	@Test
	@WithMockOAuth2User
	public void shouldFinishLessonCorrectly() {
		Long courseId = getCourseId();
		Long lessonId = getLessonId(courseId);

		testInstance.finishLesson(lessonId, courseId);
		verify(kafkaService).sendFinishLessonEvent(any());
	}

	private Long getCourseId() {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle(JAVA_CORE);

		List<CourseEntity> courses = courseService.findCourses(request);
		assertEquals(1, courses.size());

		return courses.get(0).getId();
	}

	private Long getLessonId(Long courseId) {
		List<LessonEntity> actual = testInstance.getLessons(courseId);

		assertFalse(actual.isEmpty());
		assertEquals(4, actual.size());

		return actual.get(0).getId();
	}

}
