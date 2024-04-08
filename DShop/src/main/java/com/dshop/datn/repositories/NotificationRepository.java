package com.dshop.datn.repositories;

import com.dshop.datn.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> getNotificationByIsReadEqualsAndDeliverStatusEquals(Boolean isRead, Boolean deliverStatus);
}
