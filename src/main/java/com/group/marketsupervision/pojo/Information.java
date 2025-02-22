package com.group.marketsupervision.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer equipmentId;
    private String equipmentName;
    private String comment;
    private LocalDateTime createdTime;
}
