/**
 * DateTime: 2025/2/13 20:11
 * Author: LMC
 * Comments: 
**/ 
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin getAdminByUnameAndPwd(String username, String password);
//
//    @Select("select * from admin")
//    List<Admin> getAdminList();
//
    @Select("select * from admin where username = #{username}")
    Admin getAdminByUname(String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into admin(username, password, phone, created_time) " +
            "values(#{username}, #{password}, #{phone}, #{createdTime})")
    void insertAdmin(Admin admin);

}
