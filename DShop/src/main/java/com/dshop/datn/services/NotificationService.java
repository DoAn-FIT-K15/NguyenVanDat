package com.dshop.datn.services;


import com.dshop.datn.models.Notification;
import com.dshop.datn.web.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    List<NotificationResponse> loadNotification();
    NotificationResponse modifyNotification(Long id);
    void updateNotification(Long id);
    List<NotificationResponse> pushNotification();
    void createNotification(Notification notification);
}
