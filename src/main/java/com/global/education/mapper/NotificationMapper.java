package com.global.education.mapper;

import com.global.education.model.notification.NotificationEntity;
import com.global.education.controller.dto.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link NotificationEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationResponse notificationToViewWrapper(NotificationEntity notificationEntity);

    List<NotificationResponse> notificationsToListOfWrappers(List<NotificationEntity> notificationEntities);

}
