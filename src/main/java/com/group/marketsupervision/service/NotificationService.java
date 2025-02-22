package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Information;
import com.group.marketsupervision.pojo.Result;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastNotifications(List<Information> notifications) {
        notifications.forEach(notification -> {
            Result result = Result.success(notification);

            messagingTemplate.convertAndSend(
                    "/topic/equipment-alerts",
                    result
            );
        });
    }
}
