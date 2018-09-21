package com.global.shop.service.impl;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void createUserPermissionNotification(NotificationWrapper wrapper) {

        wrapper.setNotificationType(NotificationType.PERMISSION_ADMIN);
        Notification notificationToAdmin = buildNotification(wrapper);

        //TODO notification for user (and push to DB template)
        //Notification notificationToUser = notificationRepository.
        //notificationRepository.saveAndFlush(notificationToAdmin);
    }

    private Notification buildNotification(NotificationWrapper wrapper) {

        Notification notification = new Notification();
        notification.setIdOfEntity(wrapper.getIdOfEntity());
        notification.setNotificationType(wrapper.getNotificationType());
        notification.setNotificationEntityType(wrapper.getNotificationEntityType());
        notification.setPublisherId(wrapper.getPublisherId());
        notification.setRecipientId(wrapper.getRecipientId());
        notification.setUpdateDate(LocalDate.now());
        return notification;
    }
}
