package com.global.shop.service.impl;

import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.mapper.NotificationMapper;
import com.global.shop.model.notification.Notification;
import com.global.shop.model.notification.NotificationTranslation;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationDTO;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.repository.NotificationTranslationRepository;
import com.global.shop.service.NotificationService;
import com.global.shop.util.ProjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if (!notificationRepository.existsById(id)) {
            throw new NotFoundRuntimeException("No available notification by id: " + id);
        }

        Notification notification = notificationRepository.findByRecipientIdAndId(user.getId(), id);
        notification.setIsRead(Boolean.TRUE);
        notificationRepository.saveAndFlush(notification);
        return notification;
    }

    @Override
    public void requestToAllowCourse(NotificationDTO dto) {

        Long adminId = projectUtils.getUserAdminId();

        Notification toAdmin = mapper.dtoToNotification(dto);
        toAdmin.setRecipientId(adminId);
        createNotification(toAdmin);


        Notification toUser = mapper.dtoToNotification(dto);
        toUser.setRecipientId(toUser.getPublisherId());  //creator - is receiver
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

    @Override
    @Transactional
    public void removeNotification(Long notificationId, User user) {

        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteByIdAndRecipientId(notificationId, user.getId());
            log.info("Notification with id: " + notificationId + " has been removed.");
        } else {
            throw new NotFoundRuntimeException("No available notification by id: " + notificationId);
        }


    }
}
