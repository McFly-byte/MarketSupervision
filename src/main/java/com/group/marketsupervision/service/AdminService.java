/**
 * DateTime: 2025/2/13 20:04
 * Author: LMC
 * Comments: 
**/ 
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;

public interface AdminService {
    /** 登录 */
    LoginInfo login(String uname, String pwd);
}
