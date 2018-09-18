package com.global.shop.repository;

import com.global.shop.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAll();

    Notification findByIssuer(String issuer);

}
