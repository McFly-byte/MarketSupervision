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
    public Result login(@RequestParam("uname") String uname,
                        @RequestParam("pwd") String pwd
    ){
        log.info("管理员登录 , {} {}", uname, pwd);
        LoginInfo loginInfo = adminService.login(uname, pwd);
        if(loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(
            @RequestParam("uname") String uname,
            @RequestParam("pwd") String pwd,
            @RequestParam("phone") String phone
    ) {
        log.info("管理员注册：{} {} {}", uname, pwd, phone);
        return adminService.register(uname, pwd, phone);
    }

    @PostMapping("rejectUC")
    public Result rejectUC(
            @RequestParam("uid") Integer uid,
            @RequestParam("ucid") Integer ucid,
            @RequestParam("comment") String comment
    ) {
        return adminService.rejectUC( ucid, comment );
    }

    @PostMapping("approvalUC")
    public Result approvalUC (
            @RequestParam("uid") Integer uid,
            @RequestParam("ucid") Integer ucid
    ) {
        return adminService.approvalUC( ucid );
    }

    @GetMapping("getAllUnverifiedCom")
    public Result getAllUnverifiedCom() {
        return adminService.getAllUnverifiedCom();
    }



}



