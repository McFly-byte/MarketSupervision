/**
 * DateTime: 2025/2/15 21:26
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.mapper;

import com.group.marketsupervision.pojo.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper {
    @Select("select * from company where cname = #{cname}")
    Company getCompanyByCname(String cname);

    @Select("select * from company")
    List<Company> getComUserList();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO company(cname, pwd, phone, createdAt) " +
            "values(#{cname}, #{pwd}, #{phone}, #{createdAt})")
    void insert(Company company);
}
