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
import java.util.ArrayList;
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
    public List<NotificationWrapper> getAllNotifications(User user) {
        List<Notification> notifications = notificationRepository.findAllByRecipientId(user.getId());
        return buildNotificationWrapper(notifications);
    }

    @Override
    public Notification getNotificationById(User user, Long id) {
        Notification notification = notificationRepository.findByRecipientIdAndId(user.getId(), id);
        notification.setIsRead(Boolean.TRUE);
        notificationRepository.saveAndFlush(notification);
        return notification;
    }

    @Override
    public void createUserRequestNotification(NotificationWrapper wrapper) {

        Long adminId = projectUtils.getUserAdminId();

        Notification toAdmin = createInfoNotificationToAdmin(wrapper, adminId);
        Notification toUser = createInfoNotificationToUser(wrapper, adminId);

        //persist
        notificationRepository.save(toAdmin);
        notificationRepository.save(toUser);
        notificationRepository.flush();
        log.info("Notification for adminId: " + toAdmin.getRecipientId() + " and for userId: "
                + toUser.getRecipientId() + " was created.");
    }

    @Override
    public void createInfoUserNotification(NotificationWrapper wrapper) {

        Long adminId = projectUtils.getUserAdminId();

        Notification toAdmin = createInfoNotificationToAdmin(wrapper, adminId);
        notificationRepository.saveAndFlush(toAdmin);
        log.info("Notification for adminId: " + toAdmin.getRecipientId() + " was created.");
    }

    @Override
    public void createResponseNotificationToUser(NotificationWrapper wrapper) {

        Notification toUser = buildNotification(wrapper);
        notificationRepository.saveAndFlush(toUser);
        log.info("Notification for user: " + wrapper.getRecipientId() + " was created.");
    }

    private Notification createInfoNotificationToAdmin(NotificationWrapper wrapper, Long adminId) {

        wrapper.setRecipientId(adminId);
        return buildNotification(wrapper);
    }

    private Notification createInfoNotificationToUser(NotificationWrapper wrapper, Long adminId) {

        wrapper.setNotificationType(NotificationType.INFO_TO_USER);
        wrapper.setRecipientId(wrapper.getPublisherId());  //creator - is receiver
        wrapper.setPublisherId(adminId);
        return buildNotification(wrapper);
    }

    private Notification buildNotification(NotificationWrapper wrapper) {

        NotificationTranslation translation = translationRepository.findByNotificationEntityTypeAndNotificationType(
                wrapper.getNotificationEntityType(), wrapper.getNotificationType());

        Notification notification = new Notification();
        notification.setIdOfEntity(wrapper.getIdOfEntity());
        notification.setNotificationType(wrapper.getNotificationType());
        notification.setNotificationEntityType(wrapper.getNotificationEntityType());
        notification.setPublisherId(wrapper.getPublisherId());
        notification.setRecipientId(wrapper.getRecipientId());
        notification.setTitle(translation.getTitle());
        notification.setUpdateDate(LocalDate.now());
        notification.setIsRead(Boolean.FALSE);
        return notification;
    }

    private List<NotificationWrapper> buildNotificationWrapper(List<Notification> notifications) {

        List<NotificationWrapper> wrappers = new ArrayList<>();
        notifications.forEach(notification -> {
            NotificationWrapper wrapper = new NotificationWrapper();
            wrapper.setId(notification.getId());
            wrapper.setPublisherId(notification.getPublisherId());
            wrapper.setRecipientId(notification.getRecipientId());
            wrapper.setNotificationType(notification.getNotificationType());
            wrapper.setNotificationEntityType(notification.getNotificationEntityType());
            wrapper.setIdOfEntity(notification.getIdOfEntity());
            wrapper.setIsRead(notification.getIsRead());
            wrappers.add(wrapper);
        });
        return wrappers;

    }
}
