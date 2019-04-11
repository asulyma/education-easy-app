package com.global.shop.service;

import com.global.shop.exception.BadRequestParametersRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.learning.LessonEntity;
import com.global.shop.model.learning.Progress;
import com.global.shop.model.user.UserEntity;
import com.global.shop.repository.LessonRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository,
            UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    public List<LessonEntity> getLessonsByCourseAndId(String nameOfCourse, Long sectionId, UserEntity userEntity) {
        return lessonRepository.findAllBySectionCourseNameAndSectionId(nameOfCourse, sectionId);
    }

    public LessonEntity getLessonById(String nameOfCourse, Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionCourseNameAndSectionIdAndId(nameOfCourse, sectionId, lessonId);
    }

    @Transactional
    public void processing(String nameOfCourse, Long sectionId, Long lessonId, Long userId) {

        LessonEntity lessonEntity =
                lessonRepository.findBySectionCourseNameAndSectionIdAndId(nameOfCourse, sectionId, lessonId);
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        UserEntity userEntity = optionalUser.orElseThrow(() ->
                new NotFoundRuntimeException("No available userEntity: " + userId));

        if (userEntity.getAlreadyDoneLessons().contains(lessonEntity)) {
            throw new BadRequestParametersRuntimeException("LessonEntity: " + lessonId
                    + " already done for userEntity: " + userId);
        }

        userEntity.getAlreadyDoneLessons().add(lessonEntity);

        //add progress after doing a lessonEntity
        long coefficient = Constants.TOTAL_PROGRESS / lessonRepository.countAllBySectionCourseName(nameOfCourse);
        Progress progress = userEntity.getProgress();
        progress.setCourseName(nameOfCourse).setProgress(progress.getProgress() + coefficient);

        log.info("LessonEntity: " + lessonId + " has been done for userEntity: '" + userEntity.getLogin());
        log.info("Add progress: " + coefficient + " for userEntity '" + userEntity.getLogin() + "'. CourseEntity: " + nameOfCourse);
    }

}
