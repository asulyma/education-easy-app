package com.global.shop.service.impl;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationTranslation;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.repository.NotificationTranslationRepository;
import com.global.shop.service.NotificationService;
import com.global.shop.util.ProjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTranslationRepository translationRepository;

    private final ProjectUtils projectUtils;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   NotificationTranslationRepository translationRepository, ProjectUtils projectUtils) {
        this.notificationRepository = notificationRepository;
        this.translationRepository = translationRepository;
        this.projectUtils = projectUtils;
    }

    @Override
    public List<Notification> getAllNotifications(User user) {
        return notificationRepository.findAllByRecipientId(user.getId());
    }

    @Override
    public void createUserRequestNotification(NotificationWrapper wrapper) {

        NotificationTranslation translation;
        Long adminId = projectUtils.getUserAdminId();

        //to admin
        translation = translationRepository.findByNotificationEntityTypeAndNotificationType(
                wrapper.getNotificationEntityType(), wrapper.getNotificationType());
        wrapper.setTitle(translation.getTitle());
        wrapper.setNotificationType(NotificationType.PERMISSION_TO_ADMIN);
        wrapper.setRecipientId(adminId);
        Notification notificationToAdmin = buildNotification(wrapper);

        //to user
        translation = translationRepository.findByNotificationEntityTypeAndNotificationType(
                wrapper.getNotificationEntityType(), wrapper.getNotificationType());
        wrapper.setTitle(translation.getTitle());
        wrapper.setNotificationType(NotificationType.PERMISSION_TO_USER);
        wrapper.setRecipientId(wrapper.getPublisherId());  //creator - is receiver
        wrapper.setPublisherId(adminId);
        Notification notificationToUser = buildNotification(wrapper);

        //persist
        notificationRepository.save(notificationToAdmin);
        notificationRepository.save(notificationToUser);
        notificationRepository.flush();
        log.info("Notification for admin: " + notificationToAdmin.getRecipientId() + " and for user: "
                + notificationToUser.getRecipientId() + " was created.");
    }

    @Override
    public void createUserResponseNotification(NotificationWrapper wrapper) {

        //to User
        NotificationTranslation translation = translationRepository
                .findByNotificationEntityTypeAndNotificationType(wrapper.getNotificationEntityType(), wrapper.getNotificationType());
        wrapper.setTitle(translation.getTitle());
        Notification notificationToUser = buildNotification(wrapper);

        //persist
        notificationRepository.saveAndFlush(notificationToUser);
        log.info("Notification for user: " + wrapper.getRecipientId() + " was created.");
    }

    private Notification buildNotification(NotificationWrapper wrapper) {

        Notification notification = new Notification();
        notification.setIdOfEntity(wrapper.getIdOfEntity());
        notification.setNotificationType(wrapper.getNotificationType());
        notification.setNotificationEntityType(wrapper.getNotificationEntityType());
        notification.setPublisherId(wrapper.getPublisherId());
        notification.setRecipientId(wrapper.getRecipientId());
        notification.setTitle(wrapper.getTitle());
        notification.setUpdateDate(LocalDate.now());
        notification.setIsRead(Boolean.FALSE);
        return notification;
    }
}
