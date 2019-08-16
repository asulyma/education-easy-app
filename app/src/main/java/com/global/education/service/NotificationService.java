package com.global.education.service;

import com.global.education.config.TranslationHolder;
import com.global.education.model.notification.NotificationEntity;
import com.global.education.model.notification.NotificationType;
import com.global.education.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.education.model.notification.EntityType.COURSE;
import static com.global.education.model.notification.NotificationType.APPROVE_PERMISSION_INFO_TO_ADMIN;
import static com.global.education.model.notification.NotificationType.APPROVE_PERMISSION_INFO_TO_USER;
import static com.global.education.model.notification.NotificationType.DECLINE_PERMISSION_INFO_TO_ADMIN;
import static com.global.education.model.notification.NotificationType.DECLINE_PERMISSION_INFO_TO_USER;
import static com.global.education.model.notification.NotificationType.INFO_TO_USER;
import static com.global.education.model.notification.NotificationType.PERMISSION_TO_ADMIN;
import static com.global.education.util.ProjectUtils.checkAndGetOptional;
import static com.global.education.util.UserUtils.getUserAdminId;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TranslationHolder translationHolder;

    public List<NotificationEntity> getNotifications(Long userId) {
        return notificationRepository.findAllByRecipientIdOrderByCreatedDateDesc(userId);
    }

    @Transactional
    public NotificationEntity getNotification(Long id) {
        NotificationEntity notification = checkAndGetOptional(notificationRepository.findById(id), id);
        notification.setRead(Boolean.TRUE);
        return notification;
    }

    public void sendRequestForCourse(Long courseId, Long userId) {
        Long adminId = getUserAdminId();

        // Create notification to ADMIN and USER (creator is receiver)
        createNotification(userId, adminId, PERMISSION_TO_ADMIN, courseId);
        createNotification(null, userId, INFO_TO_USER, courseId);
    }

    public void approveCourse(Long notificationId) {
        NotificationEntity entity = getNotification(notificationId);
        Long courseId = entity.getEntityId();
        Long userId = courseService.allowCourseForUser(entity.getPublisherId(), courseId);
        allowOrDeclineCourse(courseId, userId, APPROVE_PERMISSION_INFO_TO_ADMIN, APPROVE_PERMISSION_INFO_TO_USER);
    }

    public void declineCourse(Long notificationId) {
        NotificationEntity entity = getNotification(notificationId);
        Long userId = entity.getPublisherId();
        Long courseId = entity.getEntityId();
        allowOrDeclineCourse(courseId, userId, DECLINE_PERMISSION_INFO_TO_ADMIN, DECLINE_PERMISSION_INFO_TO_USER);
    }

    private void allowOrDeclineCourse(Long courseId, Long recipientId, NotificationType toAdmin,
            NotificationType toUser) {
        Long adminId = getUserAdminId();

        // Create notification to ADMIN and USER (he would not know about admin)
        createNotification(adminId, adminId, toAdmin, courseId);
        createNotification(null, recipientId, toUser, courseId);
    }

    private void createNotification(Long publisherId, Long recipientId, NotificationType type, Long entityId) {
        NotificationEntity notification = new NotificationEntity();
        notification.setPublisherId(publisherId);
        notification.setRecipientId(recipientId);
        notification.setNotificationType(type);
        notification.setEntityType(COURSE);
        notification.setEntityId(entityId);
        setTranslationAndSave(notification);
    }

    private void setTranslationAndSave(NotificationEntity entity) {
        entity.setDescription(translationHolder.getPair().get(entity.getNotificationType()));
        notificationRepository.saveAndFlush(entity);
        log.info("NotificationEntity for user: " + entity.getRecipientId() + " was created.");
    }

    @Transactional
    public void removeNotification(Long notificationId, Long userId) {
        int count = notificationRepository.deleteByIdAndRecipientId(notificationId, userId);
        if (count > 0) {
            log.info("NotificationEntity with id: " + notificationId + " has been removed.");
        }
    }
}
