package com.global.education.IT;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.education.common.model.Progress;
import com.global.education.controller.dto.SharedCourse;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.service.EventService;
import com.global.education.model.UserDataEntity;
import com.global.education.model.learning.CourseEntity;
import com.global.education.service.CourseService;
import com.global.education.service.UserDataService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceIT extends EducationApplicationIT {

	private static final String TEST_VALUE = "value";
	private static final String JAVA_CORE = "Java Core";
	private static final String CPP_CORE = "C++ Core";
	private static final String JS_CORE = "JavaScript Core";
	private static final String IT_TEST_COURSE = "IT-test-course";

	@Autowired
	private CourseService testInstance;

	@MockBean
	private UserDataService userDataService;
	@MockBean
	private EventService eventService;

	private final UserDataEntity user = new UserDataEntity();

	@Before
	public void init() {
		user.setUsername(TEST_VALUE);
		when(userDataService.findCurrentUser()).thenReturn(user);
	}

	@Test
	public void shouldFindCoursesWithKeyInsensitiveByTitleCorrectly() {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle("core");

		List<CourseEntity> actual = testInstance.findCourses(request);

		assertEquals(3, actual.size());

		Set<String> courseTitles = actual.stream().map(CourseEntity::getTitle).collect(Collectors.toSet());
		assertTrue(courseTitles.contains(JAVA_CORE));
		assertTrue(courseTitles.contains(CPP_CORE));
		assertTrue(courseTitles.contains(JS_CORE));
	}

	@Test
	public void shouldFindCoursesByCreatedDateCorrectly() {
		SpecificationRequest request = new SpecificationRequest();
		request.setCreatedStartDate(1601233333000L);

		List<CourseEntity> actual = testInstance.findCourses(request);
		assertEquals(2, actual.size());
	}

	@Test
	public void shouldFindCoursesByFinishDateCorrectly() {
		SpecificationRequest request = new SpecificationRequest();
		request.setFinishStartDate(1618000000000L);
		request.setFinishEndDate(1620000000000L);

		List<CourseEntity> actual = testInstance.findCourses(request);
		assertEquals(1, actual.size());
	}

	@Test(expected = NotFoundRuntimeException.class)
	public void shouldThrowExceptionWhenCourseDoesNotExist() {
		testInstance.getCourseById(333L);
	}

	@Test
	public void shouldNotStartCourseWhenAlreadyStarted() {
		user.getProgressMap().put(1L, new Progress());

		ResponseEntity<String> actual = testInstance.startCourse(1L);

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		verify(eventService, never()).sendStartCourseEvent(any());
	}

	@Test
	public void shouldStartCourseCorrectly() {
		ResponseEntity<String> actual = testInstance.startCourse(1L);

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		verify(eventService).sendStartCourseEvent(any());
	}

	@Test
	public void shouldCreateAndRemoveCourseCorrectly() {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle(IT_TEST_COURSE);
		List<CourseEntity> prepare = testInstance.findCourses(request);

		assertTrue(prepare.isEmpty());

		CourseEntity newCourse = new CourseEntity();
		newCourse.setTitle(IT_TEST_COURSE);
		testInstance.createCourse(newCourse);

		List<CourseEntity> cache = testInstance.findCourses(request);
		assertEquals(0, cache.size());

		// Need to invalidate cache
		testInstance.destroy();
		List<CourseEntity> actual = testInstance.findCourses(request);
		assertEquals(1, actual.size());

		testInstance.removeCourse(actual.get(0).getId());

		List<CourseEntity> after = testInstance.findCourses(request);
		assertTrue(after.isEmpty());
	}

	@Test(expected = NotFoundRuntimeException.class)
	public void shouldThrowExceptionWhenUpdate() {
		testInstance.updateCourse(333L, new SharedCourse());
	}

	@Test
	public void shouldUpdateCourseCorrectly() {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle(JAVA_CORE);
		List<CourseEntity> existing = testInstance.findCourses(request);

		assertEquals(1, existing.size());
		assertNotNull(existing.get(0).getCost());

		Long courseId = existing.get(0).getId();

		SharedCourse dto = new SharedCourse();
		dto.setDescription(IT_TEST_COURSE);
		dto.setTitle(JAVA_CORE);
		testInstance.updateCourse(courseId, dto);

		List<CourseEntity> actual = testInstance.findCourses(request);
		assertEquals(1, actual.size());
		assertNull(actual.get(0).getCost());
		assertEquals(IT_TEST_COURSE, actual.get(0).getDescription());
		assertEquals(courseId, actual.get(0).getId());
	}

}
