package com.group.marketsupervision.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    private int id;
    private String ename; // 设备名称
    private String ecode; // 设备代码
    private int companyId;
    private String companyName;
    private String type; // 特种设备种类
    private String registrationNumber; // 使用登记证编号
    private String version; // 设备型号规格
    private String usingPlace; // 使用地点
    private String productNumber; // 产品编号
    private Date commissioningDate; // 投入使用日期
    private Date inspectionDate; // （上次）检验日期
    private Date nextInspectionDate; // 下次检验日期
    private LocalDateTime createdAt; // 创建条目时间
    private String comment; // 备注

}
