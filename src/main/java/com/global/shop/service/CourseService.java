package com.global.shop.service;

import com.global.shop.exception.BadRequestParametersRuntimeException;
import com.global.shop.exception.NotAllowedRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.learning.CourseEntity;
import com.global.shop.model.learning.Progress;
import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.UserEntity;
import com.global.shop.repository.CourseRepository;
import com.global.shop.repository.UserRepository;
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

    public List<CourseEntity> getListOfCourse() {
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

    public void decisionOfNotification(Notification notification) {

        //change Java logic
        if (notification.getNotificationType().equals(NotificationType.APPROVE_PERMISSION)) {

            Optional<CourseEntity> optionalCourse = courseRepository.findById(notification.getIdOfEntity());
            Optional<UserEntity> optionalUser = userRepository.findById(notification.getRecipientId());

            CourseEntity courseEntity = optionalCourse.orElseThrow(() ->
                    new NotFoundRuntimeException("No available courseEntity: " + notification.getIdOfEntity()));
            UserEntity userEntity = optionalUser.orElseThrow(() ->
                    new NotFoundRuntimeException("No available userEntity:" + notification.getRecipientId()));

            if (userEntity.getAllowedCourses().contains(courseEntity)) {
                throw new BadRequestParametersRuntimeException("CourseEntity: " + courseEntity.getId()
                        + " already allowed for userEntity: " + userEntity.getId());
            }

            userEntity.getAllowedCourses().add(courseEntity);
            userEntity.setProgress(new Progress(courseEntity.getName(), 0L));
            userRepository.saveAndFlush(userEntity);
            log.info("Given access for userEntity: '" + userEntity.getLogin() + "' to courseEntity with id: "
                    + courseEntity.getId());
        }

        //send notification (approve or decline)
        notificationService.createNotification(notification);
    }
}
