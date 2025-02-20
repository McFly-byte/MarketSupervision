/**
 * DateTime: 2025/2/19 20:01
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.RegisterUser;
import com.group.marketsupervision.pojo.Result;

public interface RegisterUserService {
    Result register(RegisterUser registerUser);
}
