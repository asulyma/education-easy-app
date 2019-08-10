package com.global.education.controller.dto;

import com.global.education.model.notification.EntityType;
import com.global.education.model.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for displaying single notification.
 */
@Getter
@Setter
public class Notification {

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
