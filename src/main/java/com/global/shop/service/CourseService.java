package com.global.shop.service;

import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.NotificationWrapper;

import java.util.List;

/**
 * @version 1.0
 */
public interface CourseService {

    List<Course> getListOfCourse();

    Course getCourseById(Long id);

    void allowCourseForUser(NotificationWrapper notificationWrapper);

}
