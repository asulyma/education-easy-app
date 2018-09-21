package com.global.shop.service.impl;

import com.global.shop.model.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.CourseRepository;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.CourseService;
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
