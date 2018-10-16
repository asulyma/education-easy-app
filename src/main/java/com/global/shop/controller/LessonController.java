package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.LessonMapper;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.LessonViewWrapper;
import com.global.shop.model.wrapper.LessonWrapper;
import com.global.shop.service.LessonService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    private final ProjectUtils projectUtils;

    @Autowired
    public LessonController(LessonService lessonService,
                            LessonMapper lessonMapper,
                            ProjectUtils projectUtils) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
        this.projectUtils = projectUtils;
    }


    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<LessonWrapper>> getLessonsByCourseNameAndId(Principal principal,
                                                                         @PathVariable(name = "course") String nameOfCourse,
                                                                         @PathVariable(name = "sectionId") Long sectionId) {

        User user = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(lessonMapper.lessonsToListOfWrappers(
                lessonService.getLessonsByCourseAndId(nameOfCourse, sectionId, user)));
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<LessonViewWrapper> getLessonById(@PathVariable(name = "course") String nameOfCourse,
                                                         @PathVariable(name = "sectionId") Long sectionId,
                                                         @PathVariable(name = "id") Long lessonId) {

        return new BaseResponse<>(lessonMapper.lessonToViewWrapper(
                lessonService.getLessonById(nameOfCourse, sectionId, lessonId)));
    }


    @PutMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse processing(Principal principal,
                                   @PathVariable(name = "course") String nameOfCourse,
                                   @PathVariable(name = "sectionId") Long sectionId,
                                   @PathVariable(name = "id") Long lessonId) {

        User user = projectUtils.getUserInfo(principal);
        lessonService.processing(nameOfCourse, sectionId, lessonId, user.getId());
        return new BaseResponse();
    }
}
