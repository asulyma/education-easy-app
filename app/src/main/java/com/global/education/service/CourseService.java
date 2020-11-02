package com.global.education.service;

import static com.global.education.mapper.SpecificationMapper.INSTANCE;
import static java.lang.String.format;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.education.common.dto.event.UserStartCourseEvent;
import com.global.education.controller.dto.SharedCourse;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.model.UserDataEntity;
import com.global.education.model.learning.CourseEntity;
import com.global.education.repository.CourseRepository;
import com.global.education.service.cache.ListCache;
import com.global.education.service.specification.CourseSpecificationFactory;
import com.global.education.service.specification.SpecificationCriteria;

import lombok.extern.slf4j.Slf4j;


/**
 * Service for working with CourseEntity. Actually, there are CRUD operations for this class.
 */
@Service
@Slf4j
public class CourseService {

	private static final ListCache<CourseEntity, SpecificationRequest> CACHE = new ListCache<>();
	private static final String COURSE_ALREADY_STARTED = "Course %s is already started for user %s";
	private static final String START_COURSE = "Event Driven: event about start course %s has been sent";

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CourseSpecificationFactory specificationFactory;
	@Autowired
	private EventService eventService;
	@Autowired
	private UserDataService userDataService;

	@PostConstruct
	public void init() {
		CACHE.initCache(this::findAllBySpec, courseRepository::findAllByIdIn);
	}

	public List<CourseEntity> findCourses(SpecificationRequest request) {
		return CACHE.getCache(request);
	}

	public CourseEntity getCourseById(Long id) {
		return courseRepository.findById(id).orElseThrow(NotFoundRuntimeException::new);
	}

	public void createCourse(CourseEntity courseEntity) {
		courseRepository.save(courseEntity);
	}

	public void updateCourse(Long courseId, SharedCourse courseDto) {
		CourseEntity entity = courseRepository.findById(courseId).orElseThrow(NotFoundRuntimeException::new);
		entity.setTitle(courseDto.getTitle());
		entity.setDescription(courseDto.getDescription());
		entity.setBeginDate(courseDto.getBeginDate());
		entity.setFinishDate(courseDto.getFinishDate());
		entity.setCost(courseDto.getCost());
		entity.setAdditionalInfo(courseDto.getAdditionalInfo());
		courseRepository.save(entity);
	}

	public void removeCourse(Long courseId) {
		courseRepository.deleteById(courseId);
	}

	public ResponseEntity<String> startCourse(Long courseId) {
		UserDataEntity user = userDataService.findCurrentUser();
		if (user.getProgressMap().containsKey(courseId)) {
			return ResponseEntity.ok(format(COURSE_ALREADY_STARTED, courseId, user.getUsername()));
		}

		eventService.sendStartCourseEvent(new UserStartCourseEvent(user.getUuid(), courseId));
		return ResponseEntity.ok(format(START_COURSE, courseId));
	}

	private List<CourseEntity> findAllBySpec(SpecificationRequest request) {
		SpecificationCriteria criteria = INSTANCE.buildSpecificationCriteria(request);
		Specification<CourseEntity> specification = specificationFactory.build(criteria);
		return courseRepository.findAll(specification);
	}

	@PreDestroy
	public void destroy() {
		CACHE.invalidateCache();
	}

}
