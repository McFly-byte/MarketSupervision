/**
 * DateTime: 2025/2/19 20:03
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.RegisterUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegisterUserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO register_user(username, password, company_name, region, created_time) " +
            "values(#{username}, #{password}, #{companyName}, #{region}, #{createdTime})")
    void insertRegisterUser(RegisterUser registerUser);

    @Select("select * from register_user")
    List<RegisterUser> getAllRegisterUser();

    @Select("select * from register_user where username = #{username}")
    RegisterUser getRegisterUserByUsername(String username);

    @Select("select * from register_user where id = #{id}")
    RegisterUser getRegisterUserById(Integer id);

//    @Update("update unverifiedcom SET comment = #{comment} WHERE id = #{id} ")
//    void updateCommentById( Integer id, String comment );
//
    @Delete("DELETE FROM register_user WHERE id = #{id}")
    void deleteById( Integer id );
}
