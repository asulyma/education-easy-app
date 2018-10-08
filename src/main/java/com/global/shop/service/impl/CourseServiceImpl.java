package com.global.shop.service.impl;

import com.global.shop.exception.NotAllowedRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.User;
import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.repository.CourseRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private final NotificationService notificationService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository,
                             NotificationService notificationService) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Override
    public List<Course> getListOfCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id, User user) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        Course course = optionalCourse.orElseThrow(() -> new NotFoundRuntimeException("Course with id: " + id + " does not exist!"));

        if (course.getAllowedUsers().contains(user)) {
            return course;
        } else {
            throw new NotAllowedRuntimeException("Course: " + course.getName() + " are not allowed for user: " + user.getLogin());
        }
    }

    @Override
    public void decisionOfNotification(Notification notification) {

        //send notification (approve or decline)
        notificationService.createNotificationToUser(notification);

        //change Java logic
        if (notification.getNotificationType().equals(NotificationType.APPROVE_PERMISSION)) {

            Optional<Course> optionalCourse = courseRepository.findById(notification.getIdOfEntity());
            Optional<User> optionalUser = userRepository.findById(notification.getRecipientId());

            Course course = optionalCourse.orElseThrow(() -> new NotFoundRuntimeException("No available course: " + notification.getIdOfEntity()));
            User user = optionalUser.orElseThrow(() -> new NotFoundRuntimeException("No available user:" + notification.getRecipientId()));

            user.getAllowedCourses().add(course);
            userRepository.saveAndFlush(user);
            log.info("Given access for user: " + user.getLogin() + " to course with id: " + course.getId());
        }
    }
}
