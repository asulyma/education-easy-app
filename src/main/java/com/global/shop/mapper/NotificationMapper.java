package com.global.shop.mapper;

import com.global.shop.model.notification.NotificationEntity;
import com.global.shop.model.wrapper.NotificationDto;
import com.global.shop.model.wrapper.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link NotificationEntity} entity and DTO`s.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationResponse notificationToViewWrapper(NotificationEntity notificationEntity);

    List<NotificationResponse> notificationsToListOfWrappers(List<NotificationEntity> notificationEntities);

    NotificationEntity dtoToNotification(NotificationDto notificationDTO);
}
