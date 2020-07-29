package com.global.education.controller;

import static com.global.education.mapper.LessonMapper.INSTANCE;
import static com.global.education.service.ValidationService.ID_REGEXP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.dto.Lesson;
import com.global.education.controller.dto.SharedLesson;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.LessonService;
import com.global.education.service.ValidationService;


@RestController
@RequestMapping("/lesson")
public class LessonController extends BaseHandler {

	@Autowired
	private LessonService lessonService;
	@Autowired
	private ValidationService validationService;

	@GetMapping
	public List<Lesson> getLessonsByCourse(@RequestParam Long courseId) {
		validationService.checkUserOnAllowGetCourse(courseId);
		return INSTANCE.buildLessons(lessonService.getLessons(courseId));
	}

	@GetMapping("/{lessonId:" + ID_REGEXP + "}")
	public SharedLesson getLessonById(@PathVariable Long lessonId, @RequestParam Long courseId) {
		validationService.checkUserOnAllowGetCourse(courseId);
		return INSTANCE.buildSharedLesson(lessonService.getLessonById(lessonId, courseId));
	}

	@PostMapping("/finish/{lessonId:" + ID_REGEXP + "}")
	public ResponseEntity<String> finishLesson(@PathVariable Long lessonId, @RequestParam Long courseId) {
		validationService.checkUserOnFinishLesson(courseId, lessonId);
		return lessonService.finishLesson(lessonId, courseId);
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> createLesson(@RequestBody SharedLesson lesson) {
		lessonService.createLesson(lesson);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{id:" + ID_REGEXP + "}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> updateLesson(@PathVariable Long id, @RequestBody SharedLesson lesson) {
		lessonService.updateLesson(id, lesson);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id:" + ID_REGEXP + "}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> removeLesson(@PathVariable Long id) {
		lessonService.removeLesson(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
