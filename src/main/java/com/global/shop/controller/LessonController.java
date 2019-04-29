package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.LessonMapper;
import com.global.shop.model.user.UserEntity;
import com.global.shop.model.wrapper.LessonResponse;
import com.global.shop.service.LessonService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/{course}/{sectionId}/lessons")
public class LessonController extends BaseController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper = LessonMapper.INSTANCE;
    private final ProjectUtils projectUtils;

    @Autowired
    public LessonController(LessonService lessonService,
            ProjectUtils projectUtils) {
        this.lessonService = lessonService;
        this.projectUtils = projectUtils;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<LessonResponse>> getLessonsByCourseNameAndId(Principal principal,
            @PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long sectionId) {

        UserEntity userEntity = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(
                lessonMapper.buildLessons(lessonService.getLessonsByCourseAndId(courseName, sectionId, userEntity)));
    }

    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<LessonResponse> getLessonById(@PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long sectionId,
            @PathVariable(name = "id") Long lessonId) {

        return new BaseResponse<>(
                lessonMapper.buildLesson(lessonService.getLessonById(courseName, sectionId, lessonId)));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse processing(Principal principal,
            @PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long sectionId,
            @PathVariable(name = "id") Long lessonId) {

        UserEntity userEntity = projectUtils.getUserInfo(principal);
        lessonService.processing(courseName, sectionId, lessonId, userEntity.getId());
        return new BaseResponse();
    }
}
