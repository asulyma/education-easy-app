package com.global.education.service;

import com.education.common.model.Progress;
import com.global.education.controller.dto.Lesson;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.kafka.producer.UserUpdateEventDto;
import com.global.education.kafka.service.UserUpdateEventKafkaService;
import com.global.education.model.learning.LessonEntity;
import com.global.education.repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.education.util.Constants.TOTAL_PROGRESS;

@Slf4j
@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserUpdateEventKafkaService updateEventService;

    public List<LessonEntity> getLessons(Long courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }

    public LessonEntity getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(
                () -> new NotFoundRuntimeException("Lesson with id " + id + " does not exist!"));
    }

    @Transactional
    public void createLesson(Lesson lesson) {
        LessonEntity entity = new LessonEntity()
                .setTitle(lesson.getTitle())
                .setDescription(lesson.getDescription())
                .setCourse(courseService.getCourseById(lesson.getCourseId()));
        lessonRepository.save(entity);
    }

    @Transactional
    public void updateLesson(Long id, Lesson lesson) {
        LessonEntity entity = getLessonById(id);
        entity.setTitle(lesson.getTitle());
        entity.setDescription(lesson.getDescription());
        lessonRepository.save(entity);
    }

    @Transactional
    public void removeLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Transactional
    public void finishLesson(Long lessonId, User user) {
        LessonEntity lesson = getLessonById(lessonId);
        Progress progress = user.getProgressMap().get(lesson.getCourse().getId());

        if (progress != null && progress.getAlreadyDoneLessons().contains(lesson.getId())) {
            log.info("Lesson already done.");
            return;
        }

        updateEventService.sendUpdateEvent(buildDto(lesson, user.getId()));
        log.info("User update event has been sent. Finish lesson {}", lessonId);
    }

    private UserUpdateEventDto buildDto(LessonEntity lesson, Long userId) {
        int count = lessonRepository.countAllByCourseTitle(lesson.getCourse().getTitle());
        return new UserUpdateEventDto()
                .setCourseId(lesson.getCourse().getId())
                .setUserId(userId)
                .setAlreadyDoneLesson(lesson.getId())
                .setCoefficientToProgress(TOTAL_PROGRESS / count);
    }

}
