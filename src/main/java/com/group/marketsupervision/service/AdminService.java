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

    /** 按照名称查找企业用户 */
    Result getUserByName(String username);

    /**
     查看所有的企业用户
     */
    Result getAllUser();

    /**
     按照企业id查找设备
     */
    Result getEquipById(String id);

    /**
     * 查找所有即将逾期的设备（阈值设置成全局变量）
     */
    Result getAllEquipWillOverdue();

    /**
     * 按照企业id查找所有即将逾期的设备
     */
    Result getEquipWillExpireByUsername(String username);

    /**
     * 查找所有已经逾期的设备
     */
    Result getAllOverdueEquips();

    /**按照企业id查找所有逾期设备
     */
    Result getExpiredEquipsById(String cid);
}
