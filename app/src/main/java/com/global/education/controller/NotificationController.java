package com.global.education.controller;

import com.global.education.controller.dto.Notification;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.global.education.mapper.NotificationMapper.INSTANCE;
import static com.global.education.util.Constants.ID_REGEXP;
import static com.global.education.util.UserUtils.currentUserId;

/**
 * Controller for CRUD operations on NotificationEntity.
 */
@RestController
@RequestMapping(path = "/notification")
public class NotificationController extends BaseHandler {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getNotifications() {
        return INSTANCE.notificationsToListOfWrappers(notificationService.getNotifications(currentUserId()));
    }

    @GetMapping("/{id:" + ID_REGEXP + "}")
    public Notification getNotification(@PathVariable("id") Long id) {
        return INSTANCE.notificationToViewWrapper(notificationService.getNotification(id));
    }

    @PostMapping("/{courseId}")
    public void sendRequestForCourse(@PathVariable(name = "courseId") Long courseId) {
        notificationService.sendRequestForCourse(courseId, currentUserId());
    }

    @PostMapping("/{id}/approve")
    @Secured("ROLE_ADMIN")
    public void approveCourse(@PathVariable("id") Long notificationId) {
        notificationService.approveCourse(notificationId);
    }

    @PostMapping("/{id}/decline")
    @Secured("ROLE_ADMIN")
    public void declineCourse(@PathVariable("id") Long notificationId) {
        notificationService.declineCourse(notificationId);
    }

    @DeleteMapping("/{id:" + ID_REGEXP + "}")
    public void removeNotification(@PathVariable("id") Long notificationId) {
        notificationService.removeNotification(notificationId, currentUserId());
    }


}
