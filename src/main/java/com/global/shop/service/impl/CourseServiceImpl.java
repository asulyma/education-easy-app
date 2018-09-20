package com.global.shop.service.impl;

import com.global.shop.model.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.CourseRepository;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Course> getListOfCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }

    @Override
    public void allowCourseForUser(NotificationWrapper notificationWrapper) {

        //If admin did not allow
        if (!notificationWrapper.getDecision()) {
            return;
        }

        Notification notification = notificationRepository.findByIssuer(notificationWrapper.getIssuer());
        User user = userRepository.findByEmail(notificationWrapper.getIssuer());
        Optional<Course> optionalCourse = courseRepository.findById(notificationWrapper.getOtherId());

        //TODO change new
        Course course = optionalCourse.orElseGet(Course::new);

        List<Course> allowedCourses = user.getAllowedCourses();
        allowedCourses.add(course);

        userRepository.saveAndFlush(user);
        notificationRepository.delete(notification);

    }
}
