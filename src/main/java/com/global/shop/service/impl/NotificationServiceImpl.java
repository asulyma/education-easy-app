package com.global.shop.service.impl;

import com.global.shop.mapper.NotificationMapper;
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
    private final NotificationMapper mapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   NotificationTranslationRepository translationRepository, ProjectUtils projectUtils, NotificationMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.translationRepository = translationRepository;
        this.projectUtils = projectUtils;
        this.mapper = mapper;
    }

    @Override
    public List<Notification> getAllNotifications(User user) {
        return notificationRepository.findAllByRecipientId(user.getId());
    }

    @Override
    public Notification getNotificationById(User user, Long id) {
        Notification notification = notificationRepository.findByRecipientIdAndId(user.getId(), id);
        notification.setIsRead(Boolean.TRUE);
        notificationRepository.saveAndFlush(notification);
        return notification;
    }

    @Override
    public void requestToAllowCourse(NotificationWrapper wrapper) {

        Long adminId = projectUtils.getUserAdminId();

        Notification toAdmin = mapper.wrapperToNotification(wrapper);
        toAdmin.setRecipientId(adminId);
        createNotification(toAdmin);


        Notification toUser = mapper.wrapperToNotification(wrapper);
        toUser.setRecipientId(toUser.getPublisherId());  //creator - is receiver
        toUser.setPublisherId(adminId);
        toUser.setNotificationType(NotificationType.INFO_TO_USER);
        createNotification(toUser);
    }

    @Override
    public void createNotification(Notification notification) {

        notification.setNotificationType(notification.getNotificationType());
        NotificationTranslation translation = translationRepository.findByNotificationEntityTypeAndNotificationType(
                notification.getNotificationEntityType(), notification.getNotificationType());
        notification.setTitle(translation.getTitle());

        notificationRepository.saveAndFlush(notification);
        log.info("Notification for user: " + notification.getRecipientId() + " was created.");
    }
}
