package com.global.education.service;

import com.global.education.cache.ListCache;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.kafka.producer.UserUpdateEventDto;
import com.global.education.kafka.service.UserUpdateEventKafkaService;
import com.global.education.model.learning.CourseEntity;
import com.global.education.repository.CourseRepository;
import com.global.education.service.specification.CourseSpecificationFactory;
import com.global.education.service.specification.SpecificationCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

import static com.global.education.mapper.SpecificationMapper.INSTANCE;
import static com.global.education.util.ProjectUtils.checkAndGetOptional;
import static com.global.education.util.ProjectUtils.throwNotAllowed;
import static com.global.education.util.UserUtils.currentUser;

/**
 * Service for working with CourseEntity. Actually, there are CRUD operations for this class.
 */
@Service
@Slf4j
public class CourseService {

    private static final ListCache<CourseEntity, SpecificationRequest> CACHE = new ListCache<>();

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSpecificationFactory specificationFactory;

    @Autowired
    private UserUpdateEventKafkaService updateEventService;

    @PostConstruct
    public void init() {
        CACHE.initCache(this::findAllBySpec, this::findAllByIds);
    }

    public List<CourseEntity> findCourses(SpecificationRequest request) {
        return CACHE.getCache(request);
    }

    private List<CourseEntity> findAllBySpec(SpecificationRequest request) {
        SpecificationCriteria criteria = INSTANCE.buildSpecificationCriteria(request);
        Specification<CourseEntity> specification = specificationFactory.build(criteria);
        return courseRepository.findAll(specification);
    }

    private List<CourseEntity> findAllByIds(List<Long> ids) {
        return courseRepository.findAllByIdIn(ids);
    }

    public CourseEntity getCourseById(Long id, User user) {
        CourseEntity course = checkAndGetOptional(courseRepository.findById(id), id);
        return user.getAllowedCourses().contains(course.getId())
                ? course
                : throwNotAllowed(course, user);
    }

    public List<CourseEntity> getCourses() {
        return courseRepository.findAll();
    }

    CourseEntity getCourseById(Long id) {
        return checkAndGetOptional(courseRepository.findById(id), id);
    }

    Long allowCourseForUser(Long userId, Long courseId) {
        CourseEntity courseEntity = getCourseById(courseId);
        updateEventService.sendUpdateEvent(new UserUpdateEventDto(userId, courseEntity.getId()));
        currentUser().getAllowedCourses().add(courseId);
        log.info("User update event has been sent. Course id {}", courseId);
        return userId;
    }

}
