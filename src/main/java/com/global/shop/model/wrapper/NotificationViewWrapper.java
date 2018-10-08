package com.global.shop.model.wrapper;

import com.global.shop.model.notification.NotificationEntityType;
import com.global.shop.model.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for displaying single notification.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class NotificationViewWrapper {

    private Long id;
    private String title;
    private LocalDate updateDate;
    private Long publisherId;
    private Long recipientId;
    private NotificationType notificationType;
    private NotificationEntityType notificationEntityType;
    private Long idOfEntity;
    private Boolean isRead;

}
