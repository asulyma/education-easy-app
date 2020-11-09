package com.global.education.controller;

import static com.global.education.mapper.CourseMapper.INSTANCE;
import static com.global.education.utils.UserUtils.ID_REGEXP;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.dto.*;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.CourseService;
import com.global.education.service.ValidationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController extends BaseHandler {

	private final CourseService courseService;
	private final ValidationService validationService;

	@GetMapping
	public List<Course> getCourses(@Valid @ModelAttribute SpecificationRequest request) {
		return INSTANCE.buildCourses(courseService.findCourses(request));
	}

	@GetMapping("/{id:" + ID_REGEXP + "}")
	public SharedCourse getSharedCourse(@PathVariable Long id) {
		validationService.checkUserOnAllowGetCourse(id);
		return INSTANCE.buildSharedCourse(courseService.getCourseById(id));
	}

	@PostMapping("/start/{courseId:" + ID_REGEXP + "}")
	public ResponseEntity<String> startCourse(@PathVariable Long courseId) {
		return courseService.startCourse(courseId);
	}

	@PostMapping("/finish/{courseId:" + ID_REGEXP + "}")
	public ResponseEntity<String> finishCourse(@PathVariable Long courseId) {
		validationService.checkUserOnAllowGetCourse(courseId);
		return courseService.finishCourse(courseId);
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> createCourse(@Valid @RequestBody Course course) {
		courseService.createCourse(INSTANCE.buildEntity(course));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{id:" + ID_REGEXP + "}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> updateCourse(@PathVariable Long id, @Valid @RequestBody SharedCourse course) {
		courseService.updateCourse(id, course);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id:" + ID_REGEXP + "}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> removeCourse(@PathVariable Long id) {
		courseService.removeCourse(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
