package com.global.shop.service;

import com.global.shop.model.learning.Lesson;
import com.global.shop.model.wrapper.LessonWrapper;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface LessonService {

    List<LessonWrapper> getLessonsByCourseAndId(String nameOfCourse, Long sectionId);

    Lesson getLessonById(String nameOfCourse, Long sectionId, Long lessonId);

}
