package com.global.shop.controller;

import com.global.shop.model.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import com.global.shop.service.UserService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;
    private final CourseService courseService;
    private final ProjectUtils projectUtils;

    @Autowired
    public UserController(NotificationService notificationService, CourseService courseService,
                          UserService userService, ProjectUtils projectUtils) {
        this.notificationService = notificationService;
        this.courseService = courseService;
        this.userService = userService;
        this.projectUtils = projectUtils;
    }

    @GetMapping(path = "/")
    @Secured({"ROLE_user"})
    public String index(Principal principal) {

        User user = projectUtils.getUser(principal);

        return "external";
    }


    @GetMapping(path = "/getNotifications")
    @Secured({"ROLE_admin"})
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping(path = "/allowNotification")
    @Secured({"ROLE_admin"})
    public ResponseEntity allowNotification(@RequestBody NotificationWrapper notificationWrapper) {

        courseService.allowCourseForUser(notificationWrapper);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
