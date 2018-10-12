package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.LessonMapper;
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
public class LessonController extends BaseController {

    private final LessonService lessonService;

    private final LessonMapper lessonMapper;

    @Autowired
    public LessonController(LessonService lessonService,
                            LessonMapper lessonMapper) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
    }


    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<LessonWrapper>> getLessonsByCourseNameAndId(@PathVariable(name = "course") String nameOfCourse,
                                                                         @PathVariable(name = "sectionId") Long sectionId) {

        return new BaseResponse<>(lessonMapper.lessonsToListOfWrappers(
                lessonService.getLessonsByCourseAndId(nameOfCourse, sectionId)));
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<Lesson> getLessonById(@PathVariable(name = "course") String nameOfCourse,
                                              @PathVariable(name = "sectionId") Long sectionId,
                                              @PathVariable(name = "id") Long id) {

        return new BaseResponse<>(lessonService.getLessonById(nameOfCourse, sectionId, id));
    }
}
