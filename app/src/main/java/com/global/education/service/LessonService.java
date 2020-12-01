package com.global.education.service;

import static com.global.education.utils.UserUtils.TOTAL_PROGRESS;
import static com.global.education.utils.UserUtils.currentUserUuid;
import static java.lang.String.format;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.common.dto.event.UserFinishLessonEvent;
import com.global.education.controller.dto.SharedLesson;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.model.learning.LessonEntity;
import com.global.education.repository.LessonRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LessonService {

	private static final String FINISH_LESSON = "Event Driven: event about finish lesson %s has been sent";

	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private CourseService courseService;
	@Autowired
	private EventService updateEventService;

	public List<LessonEntity> getLessons(Long courseId) {
		return lessonRepository.findAllByCourseId(courseId);
	}

	public LessonEntity getLessonById(Long lessonId, Long courseId) {
		LessonEntity lesson = lessonRepository.findByIdAndCourseId(lessonId, courseId);
		if (lesson == null) {
			throw new NotFoundRuntimeException("Lesson with id: " + lessonId + " not found. Check course id: " + courseId);
		}
		return lesson;
	}

	public LessonEntity getLessonById(Long lessonId) {
		return lessonRepository.findById(lessonId).orElseThrow(NotFoundRuntimeException::new);
	}

	@Transactional
	public void createLesson(SharedLesson lesson) {
		LessonEntity entity = new LessonEntity().setTitle(lesson.getTitle())
				.setDescription(lesson.getDescription())
				.setCourse(courseService.getCourseById(lesson.getCourseId()))
				.setExecutionTime(lesson.getExecutionTime());
		lessonRepository.save(entity);
	}

	@Transactional
	public void updateLesson(Long id, SharedLesson lesson) {
		LessonEntity entity = getLessonById(id);
		entity.setTitle(lesson.getTitle());
		entity.setDescription(lesson.getDescription());
		entity.setExecutionTime(lesson.getExecutionTime());
		lessonRepository.save(entity);
	}

	@Transactional
	public void removeLesson(Long id) {
		lessonRepository.deleteById(id);
	}

	@Transactional
	public ResponseEntity<String> finishLesson(Long lessonId, Long courseId) {
		LessonEntity lesson = getLessonById(lessonId, courseId);
		UserFinishLessonEvent event = buildDto(lesson, currentUserUuid());

		updateEventService.sendFinishLessonEvent(event);
		return ResponseEntity.ok(format(FINISH_LESSON, lessonId));
	}

	private UserFinishLessonEvent buildDto(LessonEntity lesson, UUID userUuid) {
		int lessons = lessonRepository.countAllByCourseId(lesson.getCourse().getId());
		UserFinishLessonEvent event = new UserFinishLessonEvent();
		event.setAlreadyDoneLesson(lesson.getId());
		event.setCoefficientToProgress(TOTAL_PROGRESS / lessons);
		event.setCourseId(lesson.getCourse().getId());
		event.setUserUuid(userUuid);
		return event;
	}

}
