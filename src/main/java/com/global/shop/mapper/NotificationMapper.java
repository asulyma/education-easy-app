package com.global.shop.mapper;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.wrapper.NotificationViewWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * This class using for mapping between {@link Notification} entity and DTO`s.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface NotificationMapper {


    NotificationViewWrapper notificationToViewWrapper(Notification notification);

    List<NotificationWrapper> notificationsToListOfWrappers(List<Notification> notifications);

    Notification wrapperToNotification(NotificationWrapper notification);
}
