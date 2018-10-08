package com.global.shop.model.wrapper;

import com.global.shop.model.notification.NotificationEntityType;
import com.global.shop.model.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 *
 * DTO for communicating between front-end and back-end.
 * Front send it to back for creating notification.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class NotificationWrapper {

    private Long id;
    private Long idOfEntity;
    private Long publisherId;
    private Long recipientId;
    private NotificationType notificationType;
    private NotificationEntityType notificationEntityType;
    private Boolean isRead;
    private LocalDate updateDate;

}
