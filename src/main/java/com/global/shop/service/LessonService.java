package com.global.shop.service;

import com.global.shop.exception.BadRequestParametersRuntimeException;
import com.global.shop.exception.NotAllowedRuntimeException;
import com.global.shop.model.BaseEntity;
import com.global.shop.model.learning.LessonEntity;
import com.global.shop.model.learning.Progress;
import com.global.shop.model.user.UserEntity;
import com.global.shop.repository.LessonRepository;
import com.global.shop.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<LessonEntity> getCourseLessonsBySectionId(String courseName, Long sectionId, UserEntity userEntity) {
        List<Long> allowedSections = userEntity.getAllowedSections()
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        if (!allowedSections.contains(sectionId)) {
            throw new NotAllowedRuntimeException("No available lessons for user: " + userEntity.getId());
        }

        return lessonRepository.findAllBySectionCourseNameAndSectionId(courseName, sectionId);
    }

    public LessonEntity getLessonById(String courseName, Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionCourseNameAndSectionIdAndId(courseName, sectionId, lessonId);
    }

    @Transactional
    public void finishLesson(String courseName, Long sectionId, Long lessonId, UserEntity userEntity) {

        LessonEntity lessonEntity =
                lessonRepository.findBySectionCourseNameAndSectionIdAndId(courseName, sectionId, lessonId);

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
        log.info("Add progress: " + coefficient + " for userEntity '" + userEntity.getLogin() + "'. CourseEntity: " + courseName);
    }

}
