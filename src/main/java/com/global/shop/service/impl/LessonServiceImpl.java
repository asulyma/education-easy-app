package com.global.shop.service.impl;

import com.global.shop.model.learning.Lesson;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.LessonWrapper;
import com.global.shop.repository.LessonRepository;
import com.global.shop.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<LessonWrapper> getLessonsByCourseAndId(String nameOfCourse, Long sectionId) {

        List<Lesson> lessons = lessonRepository.findAllBySectionCourseNameAndSectionId(nameOfCourse, sectionId);
        return buildLessonWrappers(lessons);
    }

    @Override
    public Lesson getLessonById(String nameOfCourse, Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionCourseNameAndSectionIdAndId(nameOfCourse, sectionId, lessonId);
    }

    private List<LessonWrapper> buildLessonWrappers(List<Lesson> lessons) {

        List<LessonWrapper> wrappers = new ArrayList<>();
        lessons.forEach(lesson -> {
            LessonWrapper wrapper = new LessonWrapper();
            wrapper.setId(lesson.getId());
            wrapper.setTitle(lesson.getTitle());
            wrapper.setAllowedUsers(lesson.getAllowedUsers().stream().map(User::getId).collect(Collectors.toList()));
            wrapper.setAlreadyDone(lesson.getAlreadyDone().stream().map(User::getId).collect(Collectors.toList()));
            wrappers.add(wrapper);
        });

        return wrappers;
    }
}
