package com.global.shop.controller;

import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseService courseService;
    private final NotificationService notificationService;

    @Autowired
    public CourseController(CourseService courseService, NotificationService notificationService) {
        this.courseService = courseService;
        this.notificationService = notificationService;
    }

    @GetMapping
    @Secured("ROLE_user")
    public List<CourseWrapper> getListOfCourses() {
        return courseService.getListOfCourse();
    }


    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public Course getCourseById(@PathVariable(name = "id") Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping(path = "/allowCourse")
    @Secured("ROLE_user")
    public ResponseEntity sendPermissionRequestOnCourse(@RequestBody NotificationWrapper wrapper) {
        notificationService.createUserRequestNotification(wrapper);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
