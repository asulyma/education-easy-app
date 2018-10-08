package com.global.shop.mapper;

import com.global.shop.model.notification.Notification;
import com.global.shop.model.wrapper.NotificationViewWrapper;
import com.global.shop.model.wrapper.NotificationWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface NotificationMapper {

    List<NotificationViewWrapper> notificationToListOfWrappers(List<Notification> notifications);

    Notification wrapperToNotification(NotificationWrapper notification);
}
