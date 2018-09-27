package com.global.shop.controller;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final CourseService courseService;
    private final ProjectUtils projectUtils;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  CourseService courseService,
                                  ProjectUtils projectUtils) {
        this.notificationService = notificationService;
        this.courseService = courseService;
        this.projectUtils = projectUtils;
    }

    @GetMapping(path = "/")
    @Secured({"ROLE_user"})
    public List<Notification> getNotifications(Principal principal) {
        User user = projectUtils.getUser(principal);
        return notificationService.getAllNotifications(user);
    }

    @PostMapping(path = "/decision")
    @Secured({"ROLE_admin"})
    public ResponseEntity decisionOfNotification(@RequestBody NotificationWrapper notificationWrapper) {

        courseService.decisionOfNotification(notificationWrapper);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
