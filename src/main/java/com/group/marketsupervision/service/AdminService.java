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
}
