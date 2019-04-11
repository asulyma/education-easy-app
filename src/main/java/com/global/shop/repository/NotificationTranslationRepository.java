package com.global.shop.repository;

import com.global.shop.model.notification.NotificationEntityType;
import com.global.shop.model.notification.NotificationTranslation;
import com.global.shop.model.notification.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The class for working with DataBases. Only for {@link NotificationTranslation} entity.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface NotificationTranslationRepository extends JpaRepository<NotificationTranslation, Long> {

    NotificationTranslation findByNotificationEntityTypeAndNotificationType(NotificationEntityType entityType,
            NotificationType notificationType);
}
