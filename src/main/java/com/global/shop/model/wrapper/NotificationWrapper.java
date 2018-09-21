package com.global.shop.model.wrapper;

import com.global.shop.model.notification.NotificationEntityType;
import com.global.shop.model.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class NotificationWrapper {

    private Long idOfEntity;
    private Long publisherId;
    private Long recipientId;
    private NotificationType notificationType;
    private NotificationEntityType notificationEntityType;

}
