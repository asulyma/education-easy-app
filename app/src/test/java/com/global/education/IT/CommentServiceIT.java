package com.global.education.IT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.global.education.controller.dto.Comment;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.model.learning.*;
import com.global.education.service.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceIT extends EducationApplicationIT {

	private static final String TEST_VALUE = "value";
	private static final String JAVA_CORE = "Java Core";

	@Autowired
	private CommentService testInstance;
	@Autowired
	private CourseService courseService;
	@Autowired
	private LessonService lessonService;

	@Test
	@WithMockOAuth2User
	public void shouldCreateAndRemoveCommentCorrectly() {
		// PREPARATION
		CourseEntity course = getCourse();
		LessonEntity lesson = getLesson();
		List<CommentEntity> prepare = testInstance.getComments(lesson.getId());
		assertTrue(prepare.isEmpty());

		// CREATE COMMENT
		Comment dto = buildCourse(course, lesson);
		testInstance.createComment(dto);

		// VERIFY
		List<CommentEntity> comments = testInstance.getComments(lesson.getId());
		assertEquals(1, comments.size());
		assertEquals(TEST_VALUE, comments.get(0).getContent());

		// REMOVE COMMENT
		testInstance.removeComment(comments.get(0).getId());

		// VERIFY
		List<CommentEntity> actual = testInstance.getComments(lesson.getId());
		assertTrue(actual.isEmpty());
	}

	private CourseEntity getCourse() {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle(JAVA_CORE);

		List<CourseEntity> courses = courseService.findCourses(request);
		assertEquals(1, courses.size());
		return courses.get(0);
	}

	private LessonEntity getLesson() {
		Long courseId = getCourse().getId();
		List<LessonEntity> lessons = lessonService.getLessons(courseId);
		return lessons.stream().findFirst().orElseThrow(NotFoundRuntimeException::new);
	}

	private Comment buildCourse(CourseEntity course, LessonEntity lesson) {
		Comment comment = new Comment();
		comment.setCourseId(course.getId());
		comment.setLessonId(lesson.getId());
		comment.setContent(TEST_VALUE);
		return comment;
	}
}
