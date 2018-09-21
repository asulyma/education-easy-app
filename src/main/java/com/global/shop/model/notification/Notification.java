package com.global.shop.model.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
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
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 128)
    private String tittle;

    @Column(name = "update_date")
    private LocalDate updateDate;

    private Long publisherId;

    private Long recipientId;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    private NotificationEntityType notificationEntityType;

    private Long idOfEntity;
}
