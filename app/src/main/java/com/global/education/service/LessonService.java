package com.global.education.service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.global.education.controller.dto.Lesson;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.kafka.service.UserUpdateEventKafkaService;
import com.global.education.model.learning.LessonEntity;
import com.global.education.repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.global.education.service.ValidationService.TOTAL_PROGRESS;
import static com.global.education.util.UserUtils.currentUserUuid;

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
        return lessonRepository.findById(id).orElseThrow(NotFoundRuntimeException::new);
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
        LessonEntity entity = lessonRepository.getOne(id);
        entity.setTitle(lesson.getTitle());
        entity.setDescription(lesson.getDescription());
        lessonRepository.save(entity);
    }

    @Transactional
    public void removeLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Transactional
    public void finishLesson(Long lessonId) {
        LessonEntity lesson = getLessonById(lessonId);
        updateEventService.sendFinishLessonEvent(buildDto(lesson, currentUserUuid()));
        log.info("User update event has been sent. Finish lesson {}", lessonId);
    }

    private UserFinishLessonEvent buildDto(LessonEntity lesson, UUID userUuid) {
        int count = lessonRepository.countAllByCourseTitle(lesson.getCourse().getTitle());
        return new UserFinishLessonEvent()
                .setCourseId(lesson.getCourse().getId())
                .setUserUuid(userUuid)
                .setAlreadyDoneLesson(lesson.getId())
                .setCoefficientToProgress(TOTAL_PROGRESS / count);
    }

}
