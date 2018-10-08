package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.CourseMapper;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.CourseViewWrapper;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
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
@RequestMapping(value = "/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final NotificationService notificationService;

    private final ProjectUtils projectUtils;
    private final CourseMapper mapper;

    @Autowired
    public CourseController(CourseService courseService,
                            NotificationService notificationService,
                            ProjectUtils projectUtils,
                            CourseMapper mapper) {
        this.courseService = courseService;
        this.notificationService = notificationService;
        this.projectUtils = projectUtils;
        this.mapper = mapper;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<CourseWrapper>> getListOfCourses() {
        return new BaseResponse<>(mapper.courseToListOfWrappers(courseService.getListOfCourse()));
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<CourseViewWrapper> getCourseById(Principal principal,
                                                         @PathVariable(name = "id") Long courseById) {
        User userInfo = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(mapper.courseToViewWrapper(courseService.getCourseById(courseById, userInfo)));
    }

    @PostMapping(path = "/allowCourse")
    @Secured("ROLE_user")
    public BaseResponse sendPermissionRequestOnCourse(@RequestBody NotificationWrapper wrapper) {
        notificationService.requestToAllowCourse(wrapper);
        return new BaseResponse();
    }

}
