package com.global.shop.service;

import com.global.shop.controller.response.BaseResponse;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.mapper.NotificationMapper;
import com.global.shop.model.notification.EntityType;
import com.global.shop.model.notification.NotificationEntity;
import com.global.shop.model.notification.NotificationType;
import com.global.shop.model.user.UserEntity;
import com.global.shop.model.wrapper.NotificationDto;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.util.ProjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ProjectUtils projectUtils;
    private final NotificationMapper mapper = NotificationMapper.INSTANCE;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository,
                               ProjectUtils projectUtils) {
        this.notificationRepository = notificationRepository;
        this.projectUtils = projectUtils;
    }

    public List<NotificationEntity> getAllNotifications(UserEntity userEntity) {
        return notificationRepository.findAllByRecipientId(userEntity.getId());
    }

    public NotificationEntity getNotificationById(Long notificationId) {

        NotificationEntity notificationEntity = notificationRepository.findById(notificationId)
                .orElseThrow(NotFoundRuntimeException::new);

        notificationEntity.setRead(Boolean.TRUE);
        notificationRepository.saveAndFlush(notificationEntity);
        return notificationEntity;
    }

    public BaseResponse requestToPermissionOfCourse(Long courseId, NotificationDto dto) {
        Long adminId = projectUtils.getUserAdminId();

        // Create notification to ADMIN
        NotificationEntity toAdmin = mapper.dtoToNotification(dto);
        toAdmin.setRecipientId(adminId);
        toAdmin.setNotificationType(NotificationType.PERMISSION_TO_ADMIN);
        createNotification(toAdmin);

        // Create notification to USER (creator is receiver)
        NotificationEntity toUser = mapper.dtoToNotification(dto);
        toUser.setRecipientId(toUser.getPublisherId());
        toUser.setNotificationType(NotificationType.INFO_TO_USER);
        createNotification(toUser);

        return new BaseResponse();
    }

    public BaseResponse requestToAllowOrDeclinePermissionOfCourse(Long courseId, Long recipientId,
                                                                  NotificationType toAdminType,
                                                                  NotificationType toUserType) {
        Long adminId = projectUtils.getUserAdminId();

        // Create notification to ADMIN
        NotificationEntity toAdmin = new NotificationEntity();
        toAdmin.setPublisherId(adminId);
        toAdmin.setRecipientId(adminId);
        toAdmin.setNotificationType(toAdminType);
        toAdmin.setEntityType(EntityType.COURSE);
        toAdmin.setEntityId(courseId);
        createNotification(toAdmin);

        // Create notification to USER
        NotificationEntity toUser = new NotificationEntity();
        toUser.setRecipientId(recipientId);
        toUser.setNotificationType(toUserType);
        toUser.setEntityType(EntityType.COURSE);
        toUser.setEntityId(courseId);
        createNotification(toUser);

        return new BaseResponse();
    }

    public void createNotification(NotificationEntity entity) {

            //TODO description
//        TranslationEntity translation = translationRepository.findByEntityTypeAndNotificationType(
//                entity.getEntityType(), entity.getNotificationType());
//        entity.setDescription(translation.getDescription());
//
//        notificationRepository.saveAndFlush(entity);
//        log.info("NotificationEntity for user: " + entity.getRecipientId() + " was created.");
    }

    @Transactional
    public void removeNotification(Long notificationId, UserEntity userEntity) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteByIdAndRecipientId(notificationId, userEntity.getId());
            log.info("NotificationEntity with id: " + notificationId + " has been removed.");
        } else {
            throw new NotFoundRuntimeException("No available notification by id: " + notificationId);
        }


    }
}
