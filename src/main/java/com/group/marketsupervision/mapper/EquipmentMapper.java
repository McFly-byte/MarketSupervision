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
    @Insert("INSERT INTO equipment(ename, ecode, registrationNumber, version, usingPlace, companyName, type, productNumber, commissioningDate, inspectionDate, comment, nextInspectionDate, createdAt,is_inspected, is_overdue) " +
            "VALUES(#{ename}, #{ecode}, #{registrationNumber}, #{version}, #{usingPlace}, #{companyName}, #{type}, #{productNumber}, #{commissioningDate}, #{inspectionDate}, #{comment}, #{nextInspectionDate}, #{createdAt}, #{isInspected}, #{isOverdue})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertEquipment(Equipment equipment);


    @Select("SELECT * FROM equipment WHERE companyName = #{companyName}")
    List<Equipment> getEquipmentsByCompanyName(String companyName);

}