package com.global.shop.service;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationWrapper;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface NotificationService {

    List<Notification> getAllNotifications(User user);

    void createUserRequestNotification(NotificationWrapper wrapper);

    void createUserResponseNotification(NotificationWrapper wrapper);

}
