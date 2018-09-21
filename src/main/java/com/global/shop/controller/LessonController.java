package com.global.shop.controller;

import com.global.shop.model.learning.Lesson;
import com.global.shop.model.wrapper.LessonWrapper;
import com.global.shop.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/{course}/{sectionId}/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping
    @Secured("ROLE_user")
    public List<LessonWrapper> getLessonsByCourseNameAndId(@PathVariable(name = "course") String nameOfCourse,
                                                           @PathVariable(name = "sectionId") Long sectionId) {

        return lessonService.getLessonsByCourseAndId(nameOfCourse, sectionId);
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public Lesson getLessonById(@PathVariable(name = "course") String nameOfCourse,
                                @PathVariable(name = "sectionId") Long sectionId,
                                @PathVariable(name = "id") Long id) {

        return lessonService.getLessonById(nameOfCourse, sectionId, id);
    }
}
