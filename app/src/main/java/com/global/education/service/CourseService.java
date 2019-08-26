package com.global.education.service;

import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.Progress;
import com.global.education.cache.ListCache;
import com.global.education.controller.dto.Course;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
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
import static com.global.education.util.ProjectUtils.checkOnStartCourse;

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
    private UserUpdateEventKafkaService kafkaService;

    @PostConstruct
    public void init() {
        CACHE.initCache(this::findAllBySpec, courseRepository::findAllByIdIn);
    }

    public List<CourseEntity> findCourses(SpecificationRequest request) {
        return CACHE.getCache(request);
    }

    public CourseEntity getCourseById(Long id, User user) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundRuntimeException("Course with id " + id + " does not exist!"));
        checkOnStartCourse(course.getId(), user);
        return course;
    }

    public void createCourse(CourseEntity courseEntity) {
        courseRepository.save(courseEntity);
    }

    public void updateCourse(Long courseId, Course courseDto) {
        CourseEntity entity = courseRepository.findById(courseId).orElseThrow(NotFoundRuntimeException::new);
        entity.setTitle(courseDto.getTitle());
        entity.setDescription(courseDto.getDescription());
        entity.setBeginDate(courseDto.getBeginDate());
        entity.setEndDate(courseDto.getEndDate());
        entity.setCost(courseDto.getCost());
        courseRepository.save(entity);
    }

    public void removeCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public void startCourse(Long courseId, User user) {
        if (user.getProgressMap().containsKey(courseId)) {
            log.info("Course {} is already started for user {}", courseId, user.getId());
            return;
        }
        kafkaService.sendStartCourseEvent(new UserStartCourseEvent(user.getId(), courseId));
        log.info("User update event has been sent. Start course {}", courseId);

        // TODO In further change on event from Kafka, but this way is not good (if on kafka side smth was wrong)
        user.getProgressMap().put(courseId, new Progress());
    }

    CourseEntity getCourseById(Long id) {
        return courseRepository.getOne(id);
    }

    private List<CourseEntity> findAllBySpec(SpecificationRequest request) {
        SpecificationCriteria criteria = INSTANCE.buildSpecificationCriteria(request);
        Specification<CourseEntity> specification = specificationFactory.build(criteria);
        return courseRepository.findAll(specification);
    }

}
