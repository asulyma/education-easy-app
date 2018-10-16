package com.global.shop.service;

import com.global.shop.model.learning.Lesson;
import com.global.shop.model.user.User;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface LessonService {

    List<Lesson> getLessonsByCourseAndId(String nameOfCourse, Long sectionId, User user);

    Lesson getLessonById(String nameOfCourse, Long sectionId, Long lessonId);

    void processing(String nameOfCourse, Long sectionId, Long lessonId, Long userId);

}
