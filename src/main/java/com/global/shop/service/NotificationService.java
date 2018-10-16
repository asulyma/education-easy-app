package com.global.shop.service;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationDTO;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface NotificationService {

    List<Notification> getAllNotifications(User user);

    Notification getNotificationById(User user, Long id);

    void requestToAllowCourse(NotificationDTO dto);

    void createNotification(Notification notification);

    void removeNotification(Long notificationId, User user);

}
