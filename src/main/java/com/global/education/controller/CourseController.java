package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.model.user.UserEntity;
import com.global.education.model.wrapper.CourseResponse;
import com.global.education.service.CourseService;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.CourseMapper.INSTANCE;

@RestController
@RequestMapping(value = "/course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public BaseResponse<List<CourseResponse>> getCourses() {
        return new BaseResponse<>(INSTANCE.buildCourses(courseService.getCourses()));
    }

    @GetMapping("/{id}")
    public BaseResponse<CourseResponse> getCourse(Principal principal, @PathVariable(name = "id") Long id) {
        UserEntity userInfo = userUtils.getUserInfo(principal);
        return new BaseResponse<>(INSTANCE.buildCourse(courseService.getCourseById(id, userInfo)));
    }

}
