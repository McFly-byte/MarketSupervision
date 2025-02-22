/**
 * DateTime: 2025/2/16 10:03
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.pojo.User;

import java.util.List;

public interface UserService {
    Result login(User user);

    Result insertEquipment(Equipment equipment);

    Result importEquipments(List<Equipment> equipments);

    Result deleteEquipmentById(int id);

    Result getAllEquipments(Integer id);
}
