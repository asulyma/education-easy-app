package com.global.education.repository;

import com.global.education.model.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByRecipientIdOrderByCreatedDateDesc(Long recipientId);

    int deleteByIdAndRecipientId(Long id, Long recipientId);

}
