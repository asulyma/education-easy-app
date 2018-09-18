package com.global.shop.service.impl;

import com.global.shop.model.Notification;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.repository.NotificationRepository;
import com.global.shop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void createNotification(Notification notification){
        notificationRepository.saveAndFlush(notification);
    }

    @Override
    public Notification buildNotification(NotificationWrapper wrapper){

        Notification notification = new Notification();
        notification.setIssuer(wrapper.getIssuer());
        notification.setOtherId(wrapper.getOtherId());
        notification.setNotificationType(wrapper.getNotificationType());
        notification.setDecision(wrapper.getDecision());
        return notification;
    }
}
