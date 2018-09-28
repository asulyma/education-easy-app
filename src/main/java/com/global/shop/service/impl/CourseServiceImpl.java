package com.global.shop.service.impl;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.User;
import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.CourseRepository;
import com.global.shop.repository.NotificationRepository;
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
    public List<CourseWrapper> getListOfCourse() {

        List<Course> courses = courseRepository.findAll();
        return buildCourseWrappers(courses);
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }

    @Override
    public void decisionOfNotification(NotificationWrapper wrapper) {

        //send notification (approve or decline)
        notificationService.createUserResponseNotification(wrapper);

        //change Java logic
        if (wrapper.getNotificationType().equals(NotificationType.APPROVE_PERMISSION)) {

            Optional<Course> optionalCourse = courseRepository.findById(wrapper.getIdOfEntity());
            Optional<User> optionalUser = userRepository.findById(wrapper.getRecipientId());

            Course course = optionalCourse.orElse(null);
            User user = optionalUser.orElse(null);

            if (course == null || user == null) {
                log.info("Course with id: " + wrapper.getIdOfEntity() + " and user with id: "
                        + wrapper.getRecipientId() + " does not exists.");
                return;
            }
            user.getAllowedCourses().add(course);
            userRepository.saveAndFlush(user);
        }
    }


    private List<CourseWrapper> buildCourseWrappers(List<Course> courses) {

        List<CourseWrapper> wrappers = new ArrayList<>();
        courses.forEach(course -> {
            CourseWrapper wrapper = new CourseWrapper();
            wrapper.setId(course.getId());
            wrapper.setName(course.getName());
            wrapper.setTitle(course.getTitle());
            wrapper.setCost(course.getCost());
            wrapper.setAllowedUsers(course.getAllowedUsers().stream().map(User::getId).collect(Collectors.toList()));
            wrappers.add(wrapper);
        });
        return wrappers;

    }
}
