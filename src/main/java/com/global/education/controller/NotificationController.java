package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.model.user.UserEntity;
import com.global.education.model.wrapper.NotificationResponse;
import com.global.education.service.NotificationService;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.NotificationMapper.INSTANCE;

/**
 * Controller for CRUD operations on NotificationEntity.
 */
@RestController
@RequestMapping("/notification")
public class NotificationController extends BaseController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public BaseResponse<List<NotificationResponse>> getNotifications(Principal principal) {
        UserEntity userInfo = userUtils.getUserInfo(principal);
        return new BaseResponse<>(
                INSTANCE.notificationsToListOfWrappers(notificationService.getNotifications(userInfo)));
    }

    @GetMapping("/{id}")
    public BaseResponse<NotificationResponse> getNotificationById(@PathVariable("id") Long id) {
        return new BaseResponse<>(INSTANCE.notificationToViewWrapper(notificationService.getNotification(id)));
    }

    @PostMapping("/{courseId}")
    public BaseResponse getAccessForCourse(Principal principal, @PathVariable(name = "courseId") Long courseId) {
        notificationService.getAccessForCourse(courseId, userUtils.getUserInfo(principal).getId());
        return new BaseResponse();
    }

    @PostMapping("/{courseId}/approve")
    @Secured("ROLE_ADMIN")
    public BaseResponse approveCourse(@PathVariable("courseId") Long courseId, @RequestBody Long notificationId) {
        notificationService.approveCourse(courseId, notificationId);
        return new BaseResponse();
    }

    @PostMapping("/{courseId}/decline")
    @Secured("ROLE_ADMIN")
    public BaseResponse declineCourse(@PathVariable("courseId") Long courseId,
            @RequestParam("notificationId") Long notificationId) {
        notificationService.declineCourse(courseId, notificationId);
        return new BaseResponse();
    }

    @DeleteMapping("/{id}")
    public BaseResponse removeNotification(Principal principal, @PathVariable("id") Long notificationId) {
        notificationService.removeNotification(notificationId, userUtils.getUserInfo(principal));
        return new BaseResponse<>();
    }


}
