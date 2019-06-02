package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.mapper.CourseMapper;
import com.global.education.model.user.UserEntity;
import com.global.education.model.wrapper.CourseResponse;
import com.global.education.service.CourseService;
import com.global.education.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;

    private final ProjectUtils projectUtils;
    private final CourseMapper courseMapper = CourseMapper.INSTANCE;

    @Autowired
    public CourseController(CourseService courseService, ProjectUtils projectUtils) {
        this.courseService = courseService;
        this.projectUtils = projectUtils;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<CourseResponse>> getCourses() {
        return new BaseResponse<>(courseMapper.buildCourses(courseService.getCourses()));
    }

    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<CourseResponse> getCourseById(Principal principal, @PathVariable(name = "id") Long id) {
        UserEntity userEntityInfo = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(courseMapper.buildCourse(courseService.getCourseById(id, userEntityInfo)));
    }

    @PostMapping("/{courseId}/approve")
    @Secured({"ROLE_admin"})
    public BaseResponse approveCourse(@PathVariable("courseId") Long courseId,
                                      @RequestParam("notificationId") Long notificationId) {
        return courseService.approveCourse(courseId, notificationId);
    }

    @PostMapping("/{courseId}/decline")
    @Secured({"ROLE_admin"})
    public BaseResponse declineCourse(@PathVariable("courseId") Long courseId,
                                      @RequestParam("notificationId") Long notificationId) {
        return courseService.declineCourse(courseId, notificationId);
    }

}
