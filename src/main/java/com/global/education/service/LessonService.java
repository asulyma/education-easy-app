package com.global.education.service;

import com.global.education.model.learning.LessonEntity;
import com.global.education.model.learning.Progress;
import com.global.education.model.user.UserEntity;
import com.global.education.repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.education.util.Constants.TOTAL_PROGRESS;
import static com.global.education.util.ProjectUtils.checkAndGetOptional;

@Slf4j
@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonEntity> getLessons(String courseName, Long sectionId) {
        return lessonRepository.findAllBySectionCourseNameAndSectionId(courseName, sectionId);
    }

    public LessonEntity getLessonById(Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionIdAndId(sectionId, lessonId);
    }

    public LessonEntity getLessonById(Long lessonId) {
        return checkAndGetOptional(lessonRepository.findById(lessonId), lessonId);
    }

    @Transactional
    public void finishLesson(String courseName, Long sectionId, Long lessonId, UserEntity userEntity) {
        LessonEntity lesson = getLessonById(sectionId, lessonId);

        if (userEntity.getAlreadyDoneLessons().contains(lesson)) {
            log.info("Lesson already done.");
            return;
        }
        userEntity.getAlreadyDoneLessons().add(lesson);

        Progress progress = userEntity.getProgress();
        if (progress == null) {
            progress = new Progress();
        }
        progress.setCourseName(courseName)
                .setProgressValue(progress.getProgressValue() + getCoefficient(courseName));
        userEntity.setProgress(progress);

        log.info("Lesson: " + lessonId + " has been done for user: " + userEntity.getLogin());
        log.info("Add progress for user " + userEntity.getLogin());
    }

    /**
     * Calculate coefficient for progress
     */
    private long getCoefficient(String courseName) {
        return TOTAL_PROGRESS / lessonRepository.countAllBySectionCourseName(courseName);
    }

}
