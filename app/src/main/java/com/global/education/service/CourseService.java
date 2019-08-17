package com.global.education.service;

import com.global.education.cache.ListCache;
import com.global.education.controller.dto.Course;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
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

    @PostConstruct
    public void init() {
        CACHE.initCache(this::findAllBySpec, this::findAllByIds);
    }

    public List<CourseEntity> findCourses(SpecificationRequest request) {
        return CACHE.getCache(request);
    }

    public CourseEntity getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new NotFoundRuntimeException("Course with id " + id + " does not exist!"));
    }

    public List<CourseEntity> getCourses() {
        return courseRepository.findAll();
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

    private List<CourseEntity> findAllBySpec(SpecificationRequest request) {
        SpecificationCriteria criteria = INSTANCE.buildSpecificationCriteria(request);
        Specification<CourseEntity> specification = specificationFactory.build(criteria);
        return courseRepository.findAll(specification);
    }

    private List<CourseEntity> findAllByIds(List<Long> ids) {
        return courseRepository.findAllByIdIn(ids);
    }

}
