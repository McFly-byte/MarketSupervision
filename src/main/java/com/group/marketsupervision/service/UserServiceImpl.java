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

        if (!SecurityUtils.matchesPassword(user.getPassword(), userLogin.getPassword())) {
            return null;
        }
        String jwt = JwtUtils.genJwt(userLogin.getUsername());
        LoginInfo loginInfo = new LoginInfo(userLogin.getId(), userLogin.getUsername(), jwt);
        return Result.success(loginInfo);
    }

    @Override
    public Result insertEquipment(Equipment equipment) {
        equipment.setCreatedAt(LocalDateTime.now());
        equipmentMapper.insertEquipment(equipment);
        return Result.success(equipment);
    }

    @PostMapping("/importEquipments")
    public Result importEquipments(List<Equipment> equipments) {
        for (Equipment equipment : equipments) {
            String companyName = equipment.getCompanyName();
            equipment.setCreatedAt(LocalDateTime.now());
            equipment.setIsInspected(equipment.isInspected());
            equipment.setIsOverdue(equipment.isOverdue());
            equipmentMapper.insertEquipment(equipment);
        }
        return Result.success(equipments);
    }


}
