/**
 * DateTime: 2025/2/15 20:12
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.UnverifiedCom;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UCMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO unverifiedcom(cname, pwd, phone, createdAt) " +
            "values(#{cname}, #{pwd}, #{phone}, #{createdAt})")
    void insertUC(UnverifiedCom unverifiedCom);

    @Select("select * from unverifiedcom")
    List<UnverifiedCom> getUCList();

    @Select("select * from unverifiedcom where cname = #{ucname}")
    UnverifiedCom getUcByUCName(String ucname);

    @Select("select * from unverifiedcom where id = #{id}")
    UnverifiedCom getById(Integer id);

    @Update("update unverifiedcom SET comment = #{comment} WHERE id = #{id} ")
    void updateCommentById( Integer id, String comment );

    @Delete("DELETE FROM unverifiedcom WHERE id = #{id}")
    void deleteById( Integer id );
}
