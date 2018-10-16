package com.global.shop.repository;

import com.global.shop.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class for working with DataBases.
 * Only for {@link Notification} entity.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByRecipientId(Long recipientId);

    Notification findByRecipientIdAndId(Long recipientId, Long id);

    void deleteByIdAndRecipientId(Long id, Long recipientId);

}
