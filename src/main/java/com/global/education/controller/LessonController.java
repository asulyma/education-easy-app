package com.global.education.controller;

import com.global.education.controller.dto.Lesson;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.LessonService;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.LessonMapper.INSTANCE;
import static com.global.education.util.Constants.ID_REGEXP;

@RestController
@RequestMapping(path = "/lesson")
public class LessonController extends BaseHandler {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public List<Lesson> getLessons(@RequestParam(name = "course") String courseName,
            @RequestParam(name = "sectionId") Long sectionId) {
        return INSTANCE.buildLessons(lessonService.getLessons(courseName, sectionId));
    }

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Lesson getLessonById(@PathVariable(name = "id") Long lessonId) {
        return INSTANCE.buildLesson(lessonService.getLessonById(lessonId));
    }

    @PutMapping("/{id:" + ID_REGEXP + "}")
    public void finishLesson(Principal principal, @PathVariable(name = "id") Long lessonId,
            @RequestParam(name = "course") String courseName) {
        lessonService.finishLesson(courseName, lessonId, userUtils.getUserInfo(principal));
    }
}
