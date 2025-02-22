package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.EquipmentMapper;
import com.group.marketsupervision.mapper.InformationMapper;
import com.group.marketsupervision.mapper.UserMapper;
import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.Information;
import com.group.marketsupervision.pojo.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class EquipmentServieImpl implements EquipmentService{

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public List<Information> checkAndUpdateOverdueStatus() {
        List<Information> newNotifications = new ArrayList<>();
        // 1. 检查并更新逾期状态
        List<Equipment> overdueEquipments = equipmentMapper.getAllEquipments()
                .stream()
                .filter(e -> e.getNextInspectionDate() != null &&
                        LocalDate.now().isAfter(e.getNextInspectionDate()))
                .collect(Collectors.toList());

        overdueEquipments.forEach(equipment -> {
            // 更新设备状态
            equipmentMapper.updateOverdueStatus(equipment.getId(), 1);

            // 写入information表
            String companyName = equipment.getCompanyName();
            User user = userMapper.getUserByCompanyName(companyName);
            Information info = new Information();
            info.setEquipmentId(equipment.getId());
            info.setEquipmentName(equipment.getEname());
            info.setUserId(user.getId());
            info.setUsername(user.getUsername());
            info.setComment("设备已逾期，下次检验日期：" + equipment.getNextInspectionDate() +"，请尽快检验");
            info.setCreatedTime(LocalDateTime.now());
            log.info("设备逾期，写入信息表：{}", info);
            informationMapper.insertInformation(info);
            newNotifications.add(info);
        });
        return newNotifications;
    }

    @Transactional
    public List<Information> checkWillOverdueEquipment() {
        List<Information> newNotifications = new ArrayList<>();
        LocalDate thresholdDate = LocalDate.now().plusDays(60);
        List<Equipment> allEquipments = equipmentMapper.getAllEquipments();
        List<Equipment> willOverdueEquipments = allEquipments.stream()
                .filter(e -> e.getNextInspectionDate() != null &&
                        LocalDate.now().isBefore(e.getNextInspectionDate()) &&
                        thresholdDate.isAfter(e.getNextInspectionDate()))
                .collect(Collectors.toList());

        willOverdueEquipments.forEach(equipment -> {
            String companyName = equipment.getCompanyName();
            User user = userMapper.getUserByCompanyName(companyName);
            Information info = new Information();
            info.setEquipmentId(equipment.getId());
            info.setEquipmentName(equipment.getEname());
            info.setUserId(user.getId());
            info.setUsername(user.getUsername());
            info.setComment("设备即将逾期，下次检验日期：" + equipment.getNextInspectionDate() +"，请尽快检验");
            info.setCreatedTime(LocalDateTime.now());
            log.info("设备即将逾期：{}", info);
            informationMapper.insertInformation(info);
            newNotifications.add(info);
        });
        return newNotifications;
    }
}
