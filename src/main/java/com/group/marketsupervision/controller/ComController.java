package com.group.marketsupervision.controller;

import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.service.ComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/com")
public class ComController {

    @Autowired
    private ComService comService;

    @PostMapping("/login")
    public Result login(@RequestParam("cname") String cname,
                        @RequestParam("pwd") String pwd
    ) {
        log.info("企业登录 , {} {}", cname, pwd);
        LoginInfo loginInfo = comService.login(cname, pwd);
        if(loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/addEquip")
    public Result addEquip( @RequestBody Equipment equipment) {
        log.info("企业添加设备 , {}", equipment);
        return comService.insertEquipment(equipment);
    }

}
