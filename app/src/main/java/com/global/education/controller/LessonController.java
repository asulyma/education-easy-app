package com.global.education.controller;

import com.global.education.controller.dto.Lesson;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.global.education.mapper.LessonMapper.INSTANCE;
import static com.global.education.util.ProjectUtils.ID_REGEXP;
import static com.global.education.util.UserUtils.currentUser;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/lesson", produces = APPLICATION_JSON_VALUE)
public class LessonController extends BaseHandler {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getLessons(@RequestParam(name = "courseId") Long courseId) {
        return INSTANCE.buildLessons(lessonService.getLessons(courseId, currentUser()));
    }

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Lesson getLessonById(@PathVariable(name = "id") Long lessonId) {
        return INSTANCE.buildLesson(lessonService.getLessonById(lessonId, currentUser()));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> createLesson(@RequestBody Lesson lesson) {
        lessonService.createLesson(lesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id:" + ID_REGEXP + "}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> updateLesson(@PathVariable(name = "id") Long id, @RequestBody Lesson lesson) {
        lessonService.updateLesson(id, lesson);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id:" + ID_REGEXP + "}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> removeLesson(@PathVariable(name = "id") Long id) {
        lessonService.removeLesson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/finish/{id:" + ID_REGEXP + "}")
    public ResponseEntity<HttpStatus> finishLesson(@PathVariable(name = "id") Long lessonId) {
        lessonService.finishLesson(lessonId, currentUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
