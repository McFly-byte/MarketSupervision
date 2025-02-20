/**
 * DateTime: 2025/2/16 10:17
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Company;
import com.group.marketsupervision.pojo.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface EquipmentMapper {
    @Insert("INSERT INTO equipment(ename, ecode, registrationNumber, version, usingPlace, companyId, companyName, type, productNumber, commissioningDate, inspectionDate, comment, nextInspectionDate, createdAt) " +
            "VALUES(#{ename}, #{ecode}, #{registrationNumber}, #{version}, #{usingPlace}, #{companyId}, #{companyName}, #{type}, #{productNumber}, #{commissioningDate}, #{inspectionDate}, #{comment}, #{nextInspectionDate}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertEquipment(Equipment equipment);

    @Select("SELECT * FROM equipment WHERE id = #{id}")
    Equipment getEquipmentById(int id);

    @Select("SELECT * FROM equipment WHERE companyId = #{companyId}")
    List<Equipment> getEquipmentsByCompanyId(int companyId);

    @Select("SELECT * FROM equipment")
    List<Equipment> getAllEquipments();

    @Update("UPDATE equipment SET ename=#{ename}, ecode=#{ecode}, registrationNumber=#{registrationNumber}, version=#{version}, usingPlace=#{usingPlace}, companyId=#{companyId}, companyName=#{companyName}, type=#{type}, productNumber=#{productNumber}, commissioningDate=#{commissioningDate}, inspectionDate=#{inspectionDate}, comment=#{comment}, nextInspectionDate=#{nextInspectionDate} WHERE id=#{id}")
    void updateEquipment(Equipment equipment);

    @Delete("DELETE FROM equipment WHERE id = #{id}")
    void deleteEquipment(int id);

    @Select("SELECT * FROM equipment WHERE nextInspectionTime <= DATE_ADD(NOW(), INTERVAL 2 MONTH)")
    List<Equipment> getEquipmentNearExpiry();

    @Select("SELECT * FROM equipment WHERE companyId = #{cid} AND nextInspectionTime <= DATE_ADD(NOW(), INTERVAL 2 MONTH)")
    List<Equipment> getEquNearExpiryByCid(Integer cid);

    @Select("SELECT * FROM equipment WHERE nextInspectionTime < NOW()")
    List<Equipment> getAllExpiredEquipment();

    @Select("SELECT * FROM equipment WHERE companyId = #{cid} AND nextInspectionTime < NOW()")
    List<Equipment>getAllExpiredEquByCid(Integer cid);

}