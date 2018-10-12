package com.global.shop.service.impl;

import com.global.shop.model.learning.Lesson;
import com.global.shop.repository.LessonRepository;
import com.global.shop.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getLessonsByCourseAndId(String nameOfCourse, Long sectionId) {

        return lessonRepository.findAllBySectionCourseNameAndSectionId(nameOfCourse, sectionId);
    }

    @Override
    public Lesson getLessonById(String nameOfCourse, Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionCourseNameAndSectionIdAndId(nameOfCourse, sectionId, lessonId);
    }
}
