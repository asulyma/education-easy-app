package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final NotificationService notificationService;

    @Autowired
    public CourseController(CourseService courseService, NotificationService notificationService) {
        this.courseService = courseService;
        this.notificationService = notificationService;
    }

    @GetMapping
    @Secured("ROLE_user")
    public BaseResponse<List<CourseWrapper>> getListOfCourses() {
        return new BaseResponse<>(courseService.getListOfCourse());
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse<Course> getCourseById(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(courseService.getCourseById(id));
    }

    @PostMapping(path = "/allowCourse")
    @Secured("ROLE_user")
    public BaseResponse sendPermissionRequestOnCourse(@RequestBody NotificationWrapper wrapper) {
        notificationService.createUserRequestNotification(wrapper);
        return new BaseResponse();
    }

}
