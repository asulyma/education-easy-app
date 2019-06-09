package com.global.education.repository;

import com.global.education.model.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link NotificationEntity} class.
 */
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByRecipientId(Long recipientId);

    void deleteByIdAndRecipientId(Long id, Long recipientId);

}
