package com.global.shop.controller;

import com.global.shop.model.Notification;
import com.global.shop.model.learning.Course;
import com.global.shop.model.learning.Section;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import com.global.shop.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    private final CourseService courseService;
    private final SectionService sectionService;
    private final NotificationService notificationService;

    @Autowired
    public CourseController(CourseService courseService, SectionService sectionService, NotificationService notificationService) {
        this.courseService = courseService;
        this.sectionService = sectionService;
        this.notificationService = notificationService;
    }

    @GetMapping(path = "/getCourses")
    @Secured("ROLE_user")
    public List<Course> getListOfCourses() {
        return courseService.findAll();
    }

    @GetMapping(path = "{id}/getSections")
    @Secured("ROLE_user")
    public List<Section> getListOfSectionsByCourseId(@PathVariable(name = "id") Long id) {
        return sectionService.findAllByCourseId(id);
    }

    @PostMapping(path = "/allowCourseRequest")
    @Secured("ROLE_user")
    public ResponseEntity requestToAllowCourse(@RequestBody NotificationWrapper notificationWrapper) {

        Notification newNotification = notificationService.buildNotification(notificationWrapper);
        notificationService.createNotification(newNotification);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
