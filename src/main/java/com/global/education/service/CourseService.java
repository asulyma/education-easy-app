package com.global.education.service;

import com.global.education.controller.response.BaseResponse;
import com.global.education.exception.BadRequestParametersRuntimeException;
import com.global.education.exception.NotAllowedRuntimeException;
import com.global.education.exception.NotFoundRuntimeException;
import com.global.education.model.learning.CourseEntity;
import com.global.education.model.learning.Progress;
import com.global.education.model.notification.NotificationEntity;
import com.global.education.model.notification.NotificationType;
import com.global.education.model.user.UserEntity;
import com.global.education.repository.CourseRepository;
import com.global.education.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private final NotificationService notificationService;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepository,
                         NotificationService notificationService) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public List<CourseEntity> getCourses() {
        return courseRepository.findAll();
    }

    public CourseEntity getCourseById(Long id, UserEntity userEntity) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        CourseEntity courseEntity = optionalCourse.orElseThrow(() ->
                new NotFoundRuntimeException("CourseEntity with id: " + id + " does not exist!"));

        if (userEntity.getAllowedCourses().contains(courseEntity)) {
            return courseEntity;
        } else {
            throw new NotAllowedRuntimeException(
                    "CourseEntity with id " + courseEntity.getId() + " are not allowed for userEntity: " + userEntity.getLogin());
        }
    }

    public BaseResponse approveCourse(Long courseId, Long notificationId) {

        NotificationEntity notificationEntity = notificationService.getNotificationById(notificationId);
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(NotFoundRuntimeException::new);

        Long userId = notificationEntity.getPublisherId();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(NotFoundRuntimeException::new);


        if (userEntity.getAllowedCourses().contains(courseEntity)) {
            throw new BadRequestParametersRuntimeException("CourseEntity: " + courseEntity.getId()
                    + " already allowed for userEntity: " + userEntity.getId());
        }

        userEntity.getAllowedCourses().add(courseEntity);
        userEntity.setProgress(new Progress(courseEntity.getName(), 0L));
        userRepository.saveAndFlush(userEntity);
        log.info("Given access for userEntity: '" + userEntity.getLogin() + "' to courseEntity with id: "
                + courseEntity.getId());

        return notificationService.requestToAllowOrDeclinePermissionOfCourse(courseEntity.getId(), userEntity.getId(),
                NotificationType.APPROVE_PERMISSION_INFO_TO_ADMIN, NotificationType.APPROVE_PERMISSION_INFO_TO_USER);
    }

    public BaseResponse declineCourse(Long courseId, Long notificationId){
        NotificationEntity notificationEntity = notificationService.getNotificationById(notificationId);
        Long userId = notificationEntity.getPublisherId();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(NotFoundRuntimeException::new);

        return notificationService.requestToAllowOrDeclinePermissionOfCourse(courseId, userEntity.getId(),
                NotificationType.DECLINE_PERMISSION_INFO_TO_ADMIN, NotificationType.DECLINE_PERMISSION_INFO_TO_USER);

    }

}
