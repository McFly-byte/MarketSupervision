package com.group.marketsupervision.scheduler;

import com.group.marketsupervision.pojo.Information;
import com.group.marketsupervision.service.EquipmentService;
import com.group.marketsupervision.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EquipmentCheckScheduler {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private NotificationService notificationService;


    @Scheduled(cron = "${equipment.check.cron:0 0 1 * * ?}")
//    @Scheduled(cron = "${equipment.check.cron:0 * * * * ?}")
    public void checkOverdueEquipment() {
        log.info("开始执行设备逾期检查...");
        List<Information> newNotifications = equipmentService.checkAndUpdateOverdueStatus();
        // 发送WebSocket通知
        if (!newNotifications.isEmpty()) {
            notificationService.broadcastNotifications(newNotifications);
        }

        log.info("开始执行设备即将逾期检查...");
        List<Information> newNotifications2 = equipmentService.checkWillOverdueEquipment();
        if (!newNotifications.isEmpty()) {
            notificationService.broadcastNotifications(newNotifications2);
        }
    }

}
