package com.global.education.model.wrapper;

import com.global.education.model.notification.EntityType;
import com.global.education.model.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class NotificationDto {

    private Long id;
    private Long publisherId;
    private Long recipientId;
    private NotificationType notificationType;
    private EntityType entityType;
    private Boolean isRead;
    private LocalDate updateDate;

}
