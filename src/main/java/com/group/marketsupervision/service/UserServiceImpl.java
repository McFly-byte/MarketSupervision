package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.EquipmentMapper;
import com.group.marketsupervision.mapper.UserMapper;
import com.group.marketsupervision.pojo.User;
import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.util.JwtUtils;
import com.group.marketsupervision.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Result login(User user) {
        User userLogin = userMapper.getUserByUsername(user.getUsername());
        // fixme 因为是通过cname检索，所以cname一定要唯一。 在uc注册时还要到company表查重

        if(userLogin == null){
            return Result.error("用户不存在");
        }

        if (!Objects.equals(user.getPassword(), userLogin.getPassword())) {
            return Result.error("密码错误");
        }
        String jwt = JwtUtils.genJwt(userLogin.getUsername());
        LoginInfo loginInfo = new LoginInfo(userLogin.getId(), userLogin.getUsername(), userLogin.getCompanyName(), jwt);
        return Result.success(loginInfo);
    }

    @Override
    public Result insertEquipment(Equipment equipment) {
        Equipment existEquipment = equipmentMapper.getEquipmentByRegistrationNumber(equipment.getRegistrationNumber());
        if ( existEquipment != null) {
            equipment.setModifiedAt(LocalDateTime.now());
            equipment.setIsInspected(equipment.isInspected());
            equipment.setIsOverdue(equipment.isOverdue());
            equipmentMapper.updateEquipmentByRegistrationNumber(equipment);
            return Result.success("设备信息已存在，修改成功");
        }
        equipment.setCreatedAt(LocalDateTime.now());
        equipment.setModifiedAt(LocalDateTime.now());
        equipment.setIsInspected(equipment.isInspected());
        equipment.setIsOverdue(equipment.isOverdue());
        equipmentMapper.insertEquipment(equipment);
        return Result.success("添加成功");
    }

    public Result importEquipments(List<Equipment> equipments) {
        for (Equipment equipment : equipments) {
            Equipment existEquipment = equipmentMapper.getEquipmentByRegistrationNumber(equipment.getRegistrationNumber());
            if ( existEquipment != null) {
                equipment.setIsInspected(equipment.isInspected());
                equipment.setIsOverdue(equipment.isOverdue());
                equipment.setCreatedAt(existEquipment.getCreatedAt());
                equipment.setModifiedAt(LocalDateTime.now());
                equipmentMapper.updateEquipmentByRegistrationNumber(equipment);
            }
            else {
                equipment.setCreatedAt(LocalDateTime.now());
                equipment.setIsInspected(equipment.isInspected());
                equipment.setIsOverdue(equipment.isOverdue());
                equipmentMapper.insertEquipment(equipment);
            }
        }
        return Result.success( "导入成功");
    }

    public Result deleteEquipmentById(int id) {
        if (equipmentMapper.existsById(id) == null) {
            return Result.error("设备不存在");
        }
        equipmentMapper.deleteEquipmentById(id);
        return Result.success("删除成功");
    }

    public Result getAllEquipments(Integer id) {
        User user = userMapper.getUserById(id);
        String companyName = user.getCompanyName();
        List<Equipment> equipments = equipmentMapper.getEquipmentsByCompanyName(companyName);
        return Result.success(equipments);
    }


}
