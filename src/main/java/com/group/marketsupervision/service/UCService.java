/**
 * DateTime: 2025/2/15 20:36
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.pojo.UnverifiedCom;

public interface UCService {
    Result register(String ucname, String pwd, String phone);

}
