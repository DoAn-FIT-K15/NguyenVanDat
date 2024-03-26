package com.dshop.backend.repositories;

import com.dshop.backend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> getNotificationByIsReadEqualsAndDeliverStatusEquals(Boolean isRead, Boolean deliverStatus);
}
