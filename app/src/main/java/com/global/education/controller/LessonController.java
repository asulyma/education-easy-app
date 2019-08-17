package com.global.education.controller;

import com.global.education.controller.dto.Lesson;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.global.education.mapper.LessonMapper.INSTANCE;
import static com.global.education.util.Constants.ID_REGEXP;
import static com.global.education.util.UserUtils.currentUser;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/lesson", produces = APPLICATION_JSON_VALUE)
public class LessonController extends BaseHandler {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getLessons(@RequestParam(name = "courseId") Long courseId) {
        return INSTANCE.buildLessons(lessonService.getLessons(courseId));
    }

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Lesson getLessonById(@PathVariable(name = "id") Long lessonId) {
        return INSTANCE.buildLesson(lessonService.getLessonById(lessonId));
    }

    @PutMapping("/{id:" + ID_REGEXP + "}")
    public void finishLesson(@PathVariable(name = "id") Long lessonId) {
        lessonService.finishLesson(lessonId, currentUser());
    }
}
