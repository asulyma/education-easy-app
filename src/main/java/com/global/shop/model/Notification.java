package com.global.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

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

    @Size(max = 1024)
    private String description;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @Email
    private String issuer;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private Long otherId;

    private boolean decision;
}
