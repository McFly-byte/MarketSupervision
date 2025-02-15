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
    @Select("select * from admin where uname = #{uname} and pwd = #{pwd}")
    Admin getAdminByUnameAndPwd(String uname, String pwd);

    @Select("select * from admin")
    List<Admin> getAdminList();

    @Select("select * from admin where uname = #{uname}")
    Admin getAdminByUname(String uname);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into admin(uname, pwd, phone, createdAt) " +
            "values(#{uname}, #{pwd}, #{phone}, #{createdAt})")
    void insertAdmin(Admin admin);

}
