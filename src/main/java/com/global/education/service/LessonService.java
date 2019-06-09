package com.global.education.service;

import com.global.education.exception.BadRequestParametersRuntimeException;
import com.global.education.model.learning.LessonEntity;
import com.global.education.model.learning.Progress;
import com.global.education.model.user.UserEntity;
import com.global.education.repository.LessonRepository;
import com.global.education.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonEntity> getLessons(String courseName, Long sectionId) {
        return lessonRepository.findAllBySectionCourseNameAndSectionId(courseName, sectionId);
    }

    public LessonEntity getLessonById(String courseName, Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionCourseNameAndSectionIdAndId(courseName, sectionId, lessonId);
    }

    @Transactional
    public void finishLesson(String courseName, Long sectionId, Long lessonId, UserEntity userEntity) {
        LessonEntity lessonEntity = getLessonById(courseName, sectionId, lessonId);

        if (userEntity.getAlreadyDoneLessons().contains(lessonEntity)) {
            throw new BadRequestParametersRuntimeException("LessonEntity: " + lessonId
                    + " already done for userEntity: " + userEntity.getId());
        }

        userEntity.getAlreadyDoneLessons().add(lessonEntity);

        //add progress after doing a lessonEntity
        long coefficient = Constants.TOTAL_PROGRESS / lessonRepository.countAllBySectionCourseName(courseName);
        Progress progress = userEntity.getProgress();
        progress.setCourseName(courseName)
                .setProgress(progress.getProgress() + coefficient);

        log.info("LessonEntity: " + lessonId + " has been done for userEntity: '" + userEntity.getLogin());
        log.info("Add progress: " + coefficient + " for userEntity '" + userEntity.getLogin() + "'. CourseEntity: "
                + courseName);
    }

}
