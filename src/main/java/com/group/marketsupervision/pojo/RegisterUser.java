package com.group.marketsupervision.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
    private Integer id;
    private String username;
    private String password;
    private String companyName;
    private LocalDateTime createdTime;
    private String region;
}