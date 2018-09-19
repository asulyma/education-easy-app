package com.global.shop.service;

import com.global.shop.model.learning.Lesson;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface LessonService {

    List<Lesson> getLessonsBySectionId(Long sectionId);

}
