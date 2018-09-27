package com.global.shop.service.impl;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationEntityType;
import com.global.shop.model.notification.NotificationTranslation;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.repository.NotificationTranslationRepository;
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
    private final NotificationTranslationRepository translationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   NotificationTranslationRepository translationRepository) {
        this.notificationRepository = notificationRepository;
        this.translationRepository = translationRepository;
    }

    @Override
    public List<Notification> getAllNotifications(User user) {
        return notificationRepository.findAllByRecipientId(user.getId());
    }

    @Override
    public void createUserPermissionNotification(NotificationWrapper wrapper) {

        wrapper.setNotificationType(NotificationType.PERMISSION_TO_ADMIN);
        Notification notificationToAdmin = buildNotification(wrapper);
        Notification notificationToUser = buildNotificationPermissionToUser(wrapper);

        notificationRepository.save(notificationToAdmin);
        notificationRepository.save(notificationToUser);
        notificationRepository.flush();
    }

    private Notification buildNotification(NotificationWrapper wrapper) {

        Notification notification = new Notification();
        notification.setIdOfEntity(wrapper.getIdOfEntity());
        notification.setNotificationType(wrapper.getNotificationType());
        notification.setNotificationEntityType(wrapper.getNotificationEntityType());
        notification.setPublisherId(wrapper.getPublisherId());
        notification.setRecipientId(wrapper.getRecipientId());
        notification.setUpdateDate(LocalDate.now());
        notification.setIsRead(Boolean.FALSE);
        return notification;
    }

    private Notification buildNotificationPermissionToUser(NotificationWrapper wrapper) {

        NotificationTranslation translation = translationRepository
                .findByNotificationEntityTypeAndNotificationType(NotificationEntityType.COURSE, NotificationType.PERMISSION_TO_USER);

        Notification notification = new Notification();
        notification.setRecipientId(wrapper.getPublisherId());  //creator - is receiver
        notification.setUpdateDate(LocalDate.now());
        notification.setTitle(translation.getTitle());
        notification.setNotificationType(translation.getNotificationType());
        notification.setNotificationEntityType(translation.getNotificationEntityType());
        notification.setIsRead(Boolean.FALSE);
        return notification;
    }
}
