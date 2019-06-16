package com.global.education.model.wrapper;

import com.global.education.model.notification.EntityType;
import com.global.education.model.notification.NotificationType;
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
public class NotificationResponse {

    private Long id;
    private Long createdDate;
    private String description;
    private Long publisherId;
    private Long recipientId;
    private NotificationType notificationType;
    private EntityType entityType;
    private Long entityId;
    private boolean isRead;

}
