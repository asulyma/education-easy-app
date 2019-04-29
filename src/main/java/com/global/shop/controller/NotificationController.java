package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.NotificationMapper;
import com.global.shop.model.user.UserEntity;
import com.global.shop.model.wrapper.NotificationDto;
import com.global.shop.model.wrapper.NotificationResponse;
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
@RequestMapping("/notification")
public class NotificationController extends BaseController {

    private final NotificationService notificationService;

    private final ProjectUtils projectUtils;
    private final NotificationMapper mapper = NotificationMapper.INSTANCE;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  ProjectUtils projectUtils) {
        this.notificationService = notificationService;
        this.projectUtils = projectUtils;
    }

    @GetMapping
    @Secured({"ROLE_user"})
    public BaseResponse<List<NotificationResponse>> getNotifications(Principal principal) {
        UserEntity userEntity = projectUtils.getUserInfo(principal);
        return new BaseResponse<>(mapper.notificationsToListOfWrappers(notificationService.getAllNotifications(
                userEntity)));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_user"})
    public BaseResponse<NotificationResponse> getNotificationById(@PathVariable("id") Long id) {
        return new BaseResponse<>(
                mapper.notificationToViewWrapper(notificationService.getNotificationById(id)));
    }

    @PostMapping("/{id}")
    @Secured("ROLE_user")
    public BaseResponse sendPermissionRequestOnCourse(@PathVariable(name = "id") Long courseId,
                                                      @RequestBody NotificationDto dto) {
        return notificationService.requestToPermissionOfCourse(courseId, dto);
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
