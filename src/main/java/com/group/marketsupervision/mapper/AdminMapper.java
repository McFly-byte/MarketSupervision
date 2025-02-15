/**
 * DateTime: 2025/2/13 20:11
 * Author: LMC
 * Comments: 
**/ 
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where uname = #{uname} and pwd = #{pwd}")
    Admin getAdminByUnameAndPwd(String uname, String pwd);

    @Select("select * from admin")
    List<Admin> getAdminList();
}
