package com.global.shop.model.notification;

import com.global.shop.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Pojo class.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "notification")
@NoArgsConstructor
@Getter
@Setter
public class NotificationEntity extends CreatableEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "is_read")
    private boolean isRead;
}
