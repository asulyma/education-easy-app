package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
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
public class NotificationController extends BaseController {

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

    @GetMapping
    @Secured({"ROLE_user"})
    public BaseResponse<List<Notification>> getNotifications(Principal principal) {
        User user = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(notificationService.getAllNotifications(user));
    }

    @PostMapping(path = "/decision")
    @Secured({"ROLE_admin"})
    public BaseResponse decisionOfNotification(@RequestBody NotificationWrapper notificationWrapper) {
        courseService.decisionOfNotification(notificationWrapper);
        return new BaseResponse<>();
    }

}
