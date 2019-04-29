package com.global.shop.repository;

import com.global.shop.model.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases.
 * Only for {@link NotificationEntity} entity.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByRecipientId(Long recipientId);

    void deleteByIdAndRecipientId(Long id, Long recipientId);

}
