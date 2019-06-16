package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.model.wrapper.LessonResponse;
import com.global.education.service.LessonService;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.LessonMapper.INSTANCE;

@RestController
@RequestMapping(value = "/{course}/{sectionId}/lesson")
public class LessonController extends BaseController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public BaseResponse<List<LessonResponse>> getLessons(@PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long sectionId) {
        return new BaseResponse<>(INSTANCE.buildLessons(lessonService.getLessons(courseName, sectionId)));
    }

    @GetMapping("/{id}")
    public BaseResponse<LessonResponse> getLessonById(@PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long sectionId,
            @PathVariable(name = "id") Long lessonId) {

        return new BaseResponse<>(INSTANCE.buildLesson(lessonService.getLessonById(courseName, sectionId, lessonId)));
    }

    @PutMapping("/{id}")
    public BaseResponse finishLesson(Principal principal,
            @PathVariable(name = "course") String courseName,
            @PathVariable(name = "sectionId") Long sectionId,
            @PathVariable(name = "id") Long lessonId) {
        lessonService.finishLesson(courseName, sectionId, lessonId, userUtils.getUserInfo(principal));
        return new BaseResponse();
    }
}
