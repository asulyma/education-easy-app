package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.NotificationMapper;
import com.global.shop.model.user.UserEntity;
import com.global.shop.model.wrapper.NotificationDTO;
import com.global.shop.model.wrapper.NotificationViewWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.service.CourseService;
import com.global.shop.service.NotificationService;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResponse<List<NotificationWrapper>> getNotifications(Principal principal) {
        UserEntity userEntity = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(mapper.notificationsToListOfWrappers(notificationService.getAllNotifications(
                userEntity)));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_user"})
    public BaseResponse<NotificationViewWrapper> getNotificationById(Principal principal,
            @PathVariable("id") Long id) {
        UserEntity userEntity = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(
                mapper.notificationToViewWrapper(notificationService.getNotificationById(userEntity, id)));
    }

    @PostMapping("/{id}")
    @Secured({"ROLE_admin"})
    public BaseResponse decisionOfNotification(@RequestBody NotificationDTO dto,
            @PathVariable("id") Long notificationId) {

        courseService.decisionOfNotification(mapper.dtoToNotification(dto));
        return new BaseResponse<>();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_user"})
    public BaseResponse removeNotification(Principal principal,
            @PathVariable("id") Long notificationId) {

        UserEntity userEntity = projectUtils.getUserInfo(principal);
        notificationService.removeNotification(notificationId, userEntity);
        return new BaseResponse<>();
    }


}
