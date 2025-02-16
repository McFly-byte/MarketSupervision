/**
 * DateTime: 2025/2/16 10:03
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;

public interface ComService {
    LoginInfo login(String cname, String pwd);

    Result insertEquipment(Equipment equipment);
}
