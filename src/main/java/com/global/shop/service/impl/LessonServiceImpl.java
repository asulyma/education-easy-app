package com.global.shop.service.impl;

import com.global.shop.exception.BadRequestParametersRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.learning.Lesson;
import com.global.shop.model.user.User;
import com.global.shop.repository.LessonRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.LessonService;
import com.global.shop.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository,
                             UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Lesson> getLessonsByCourseAndId(String nameOfCourse, Long sectionId, User user) {

        List<Lesson> lessons = lessonRepository.findAllBySectionCourseNameAndSectionId(nameOfCourse, sectionId);

        return lessons.stream().filter(e -> e.getSection().getAllowedUsers().contains(user))
                .collect(Collectors.toList());
    }

    @Override
    public Lesson getLessonById(String nameOfCourse, Long sectionId, Long lessonId) {
        return lessonRepository.findBySectionCourseNameAndSectionIdAndId(nameOfCourse, sectionId, lessonId);
    }

    @Override
    public void processing(String nameOfCourse, Long sectionId, Long lessonId, Long userId) {

        Lesson lesson = lessonRepository.findBySectionCourseNameAndSectionIdAndId(nameOfCourse, sectionId, lessonId);
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(() ->
                new NotFoundRuntimeException("No available user: " + userId));

        if (user.getAlreadyDoneLesson().contains(lesson)) {
            throw new BadRequestParametersRuntimeException("Lesson: " + lessonId
                    + " already done for user: " + userId);
        }

        user.getAlreadyDoneLesson().add(lesson);

        //add progress after doing a lesson
        long coefficient = Constants.TOTAL_PROGRESS / lessonRepository.countAllBySectionCourseName(nameOfCourse);
        user.getProgress().put(nameOfCourse, user.getProgress().get(nameOfCourse) + coefficient);

        userRepository.saveAndFlush(user);
        log.info("Lesson: " + lessonId + " has been done for user: '" + user.getLogin());
        log.info("Add progress: " + coefficient + " for user '" + user.getLogin() + "'. Course: " + nameOfCourse);
    }

}
