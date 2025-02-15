package com.group.marketsupervision.controller;

import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;



    @PostMapping("/login")
    public Result login(@RequestParam("uname") String uname, @RequestParam("pwd") String pwd){
        log.info("管理员登录 , {} {}", uname, pwd);
        LoginInfo loginInfo = adminService.login(uname, pwd);
        if(loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误~");
    }
}
