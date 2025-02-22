/**
 * DateTime: 2025/2/22 18:45
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Information;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface InformationMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into information(user_id, username, equipment_id, equipment_name, comment, created_time) " +
            "values(#{userId}, #{username}, #{equipmentId}, #{equipmentName}, #{comment}, #{createdTime})")
    void insertInformation(Information info);
}
