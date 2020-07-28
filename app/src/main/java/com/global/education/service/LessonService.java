package com.global.education.service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.global.education.controller.dto.SharedLesson;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.kafka.service.UserUpdateEventKafkaService;
import com.global.education.model.learning.LessonEntity;
import com.global.education.repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.global.education.utils.UserUtils.TOTAL_PROGRESS;
import static com.global.education.utils.UserUtils.currentUserUuid;

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

    public LessonEntity getLessonById(Long lessonId, Long courseId) {
        LessonEntity lesson = lessonRepository.findByIdAndCourseId(lessonId, courseId);
        if (lesson == null) {
            throw new NotFoundRuntimeException(
                    "Lesson with id: " + lessonId + " not found. Check course id: " + courseId);
        }
        return lesson;
    }

    @Transactional
    public void createLesson(SharedLesson lesson) {
        LessonEntity entity = new LessonEntity()
                .setTitle(lesson.getTitle())
                .setDescription(lesson.getDescription())
                .setCourse(courseService.getCourseById(lesson.getCourseId()));
        lessonRepository.save(entity);
    }

    @Transactional
    public void updateLesson(Long id, SharedLesson lesson) {
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
    public ResponseEntity<String> finishLesson(Long lessonId, Long courseId) {
        updateEventService.sendFinishLessonEvent(buildDto(getLessonById(lessonId, courseId), currentUserUuid()));
        return new ResponseEntity<>("Kafka event about finish lesson " + lessonId + " has been sent", HttpStatus.OK);
    }

    private UserFinishLessonEvent buildDto(LessonEntity lesson, UUID userUuid) {
        int lessons = lessonRepository.countAllByCourseId(lesson.getCourse().getId());
        return new UserFinishLessonEvent()
                .setCourseId(lesson.getCourse().getId())
                .setUserUuid(userUuid)
                .setAlreadyDoneLesson(lesson.getId())
                .setCoefficientToProgress(TOTAL_PROGRESS / lessons);
    }

}
