package com.global.shop.controller;

import com.global.shop.model.learning.Lesson;
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
@RequestMapping(value = "/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public List<Lesson> getLessonsBySectionId(@PathVariable("id") Long id) {
        return lessonService.getLessonsBySectionId(id);
    }
}
