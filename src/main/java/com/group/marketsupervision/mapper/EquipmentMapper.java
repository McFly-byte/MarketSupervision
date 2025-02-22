/**
 * DateTime: 2025/2/16 10:17
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface EquipmentMapper {
    void insertEquipment(Equipment equipment);

    List<Equipment> getEquipmentsByCompanyName(String companyName);

    Equipment getEquipmentByRegistrationNumber(String registrationNumber);

    void updateEquipmentByRegistrationNumber(Equipment equipment);

    void deleteEquipmentById(int id);

    Integer existsById(int id);
}