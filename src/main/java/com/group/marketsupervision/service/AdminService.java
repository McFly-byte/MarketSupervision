/**
 * DateTime: 2025/2/13 20:04
 * Author: LMC
 * Comments: 
**/ 
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;

import java.util.List;

public interface AdminService {
    /** 登录 */
    Result login(Admin admin);

    /** 注册 */
    Result register(Admin admin);

    /** 获取全部未审核企业用户 */
    Result getAllRegisterUser();

    Result reject(Integer id );

    Result approval(Integer id);

    List<Equipment> exportAllByCompanyName(String companyName);
}
