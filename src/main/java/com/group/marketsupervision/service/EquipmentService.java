/**
 * DateTime: 2025/2/22 18:37
 * Author: LMC
 * Comments:
 **/
package com.group.marketsupervision.service;

import com.group.marketsupervision.pojo.Information;

import java.util.List;

public interface EquipmentService {
    List<Information> checkAndUpdateOverdueStatus();

    List<Information> checkWillOverdueEquipment();
}
