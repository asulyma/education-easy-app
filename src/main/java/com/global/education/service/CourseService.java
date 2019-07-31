package com.global.education.service;

import com.global.education.controller.handler.exception.BadRequestParametersRuntimeException;
import com.global.education.model.learning.CourseEntity;
import com.global.education.model.user.UserEntity;
import com.global.education.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.global.education.util.ProjectUtils.checkAndGetOptional;
import static com.global.education.util.ProjectUtils.throwNotAllowed;

/**
 * Service for working with CourseEntity. Actually, there are CRUD operations for this class.
 */
@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    public List<CourseEntity> getCourses() {
        return courseRepository.findAll();
    }

    public CourseEntity getCourseById(Long id, UserEntity userEntity) {
        CourseEntity course = checkAndGetOptional(courseRepository.findById(id), id);
        return userEntity.getAllowedCourses().contains(course)
                ? course
                : throwNotAllowed(course, userEntity);
    }

    public CourseEntity getCourseById(Long id) {
        return checkAndGetOptional(courseRepository.findById(id), null);
    }

    public Long allowCourseForUser(Long userId, Long courseId) {
        CourseEntity courseEntity = getCourseById(courseId);
        UserEntity userEntity = userService.getUserById(userId);
        checkOnAlreadyAllow(courseEntity, userEntity);
        return userService.addCourseForUser(userEntity, courseEntity);
    }

    private void checkOnAlreadyAllow(CourseEntity courseEntity, UserEntity userEntity) {
        if (userEntity.getAllowedCourses().contains(courseEntity)) {
            throw new BadRequestParametersRuntimeException("CourseEntity: " + courseEntity.getId()
                    + " already allowed for userEntity: " + userEntity.getId());
        }
    }

}
