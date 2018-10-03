package com.global.shop.service;

import com.global.shop.model.learning.Course;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface CourseService {

    List<CourseWrapper> getListOfCourse();

    Course getCourseById(Long id, User user);

    void decisionOfNotification(NotificationWrapper notificationWrapper);

}
