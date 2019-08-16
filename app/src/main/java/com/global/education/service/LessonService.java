package com.global.education.service;

import com.global.education.controller.dto.User;
import com.global.education.model.learning.LessonEntity;
import com.global.education.repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.education.util.ProjectUtils.checkAndGetOptional;

@Slf4j
@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonEntity> getLessons(String courseTitle, Long sectionId) {
        return lessonRepository.findAllBySectionCourseTitleAndSectionId(courseTitle, sectionId);
    }

    public LessonEntity getLessonById(Long lessonId) {
        return checkAndGetOptional(lessonRepository.findById(lessonId), lessonId);
    }

    @Transactional
    public void finishLesson(Long lessonId, User user) {
        LessonEntity lesson = getLessonById(lessonId);

        if (user.getAlreadyDoneLessons().contains(lesson.getId())) {
            log.info("Lesson already done.");
            return;
        }
        //todo after add - send event by kafka for saving
        user.getAlreadyDoneLessons().add(lesson.getId());

        log.info("Lesson: " + lessonId + " has been done for user: " + user.getLogin());
    }

}
