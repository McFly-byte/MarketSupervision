/**
 * DateTime: 2025/2/13 20:04
 * Author: LMC
 * Comments: 
**/ 
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;

public interface AdminService {
    /** 登录 */
    LoginInfo login(String uname, String pwd);

    /** 注册 */
    Result register(String uname, String pwd, String phone);

    /** 获取全部未审核企业用户 */
    Result getAllUnverifiedCom();

    Result rejectUC(Integer ucid, String comment );

    Result approvalUC(Integer ucid);

    /** 按照名称查找企业用户 */
    Result findComUserByName(String cname);

    /**
     查看所有的企业用户
     */
    Result getAllComUser();

    /**
     按照企业id查找设备
     */
    Result findEquByCid(Integer cid);

    /**
     * 查找所有即将逾期的设备（阈值设置成全局变量）
     */
    Result getAllEquNearExpiry();

    /**
     * 按照企业id查找所有即将逾期的设备
     */
    Result getEquNearExpiryByCid(Integer cid);

    /**
     * 查找所有已经逾期的设备
     */
    Result getAllEquExpiry();

    /**按照企业id查找所有逾期设备
     */
    Result getAllExpiredEquByCid(Integer cid);
}
