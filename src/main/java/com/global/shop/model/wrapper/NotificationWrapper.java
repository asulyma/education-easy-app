package com.global.shop.model.wrapper;

import com.global.shop.model.NotificationType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class NotificationWrapper {

    private NotificationType notificationType;
    private Long otherId;
    private String issuer;
    private Boolean decision;

}
