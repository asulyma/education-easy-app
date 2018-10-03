package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.model.learning.Course;
import com.global.shop.model.user.User;
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

    @Autowired
    public CourseController(CourseService courseService, NotificationService notificationService,
                            ProjectUtils projectUtils) {
        this.courseService = courseService;
        this.notificationService = notificationService;
        this.projectUtils = projectUtils;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<CourseWrapper>> getListOfCourses() {
        return new BaseResponse<>(courseService.getListOfCourse());
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<Course> getCourseById(Principal principal,
                                              @PathVariable(name = "id") Long courseById) {
        User userInfo = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(courseService.getCourseById(courseById, userInfo));
    }

    @PostMapping(path = "/allowCourse")
    @Secured("ROLE_user")
    public BaseResponse sendPermissionRequestOnCourse(@RequestBody NotificationWrapper wrapper) {
        notificationService.createUserRequestNotification(wrapper);
        return new BaseResponse();
    }

}
