package com.group.marketsupervision.controller;

import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.pojo.UnverifiedCom;
import com.group.marketsupervision.service.UCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/uc")
public class UCController {

    @Autowired
    private UCService ucService;

    @PostMapping("/register")
    public Result register(@RequestParam("uname") String uname,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("phone") String phone) {
//        try {
            log.info("企业用户注册：{} {} {}", uname, pwd, phone);
            return ucService.register(uname, pwd, phone);
//        } catch (Exception e) {
//            return Result.error("系统出错");
//        }
    }
}
