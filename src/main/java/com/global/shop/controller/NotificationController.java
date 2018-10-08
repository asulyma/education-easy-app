package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.NotificationMapper;
import com.global.shop.model.notification.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationViewWrapper;
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
@RequestMapping("/notifications")
public class NotificationController extends BaseController {

    private final NotificationService notificationService;
    private final CourseService courseService;

    private final ProjectUtils projectUtils;
    private final NotificationMapper mapper;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  CourseService courseService,
                                  ProjectUtils projectUtils,
                                  NotificationMapper mapper) {
        this.notificationService = notificationService;
        this.courseService = courseService;
        this.projectUtils = projectUtils;
        this.mapper = mapper;
    }

    @GetMapping
    @Secured({"ROLE_user"})
    public BaseResponse<List<NotificationViewWrapper>> getNotifications(Principal principal) {
        User user = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(mapper.notificationToListOfWrappers(notificationService.getAllNotifications(user)));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_user"})
    public BaseResponse<Notification> getNotificationById(Principal principal,
                                                          @PathVariable("id") Long id) {
        User user = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(notificationService.getNotificationById(user, id));
    }

    @PostMapping(path = "/decision")
    @Secured({"ROLE_admin"})
    public BaseResponse decisionOfNotification(@RequestBody NotificationWrapper wrapper) {
        courseService.decisionOfNotification(mapper.wrapperToNotification(wrapper));
        return new BaseResponse<>();
    }

}
