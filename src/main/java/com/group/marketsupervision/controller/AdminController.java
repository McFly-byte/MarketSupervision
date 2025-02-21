package com.group.marketsupervision.controller;

import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.service.AdminService;
import com.group.marketsupervision.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173") // todo 后面改成服务器前端地址
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        log.info("管理员登录 , {} {}", admin.getUsername(), admin.getPassword());
        return adminService.login(admin);
    }

    @PostMapping("/register")
    public Result register(
            @RequestBody Admin admin
    ) {
        log.info("管理员注册：{} {} {}", admin.getUsername(), admin.getPassword(), admin.getPhone());
        return adminService.register(admin);
    }

    @PostMapping("reject")
    public Result reject(@RequestBody Integer id ) { // 企业用户id
        return adminService.reject( id );
    }

    @PostMapping("approval")
    public Result approval (@RequestBody Integer id ) { // 企业用户id
        return adminService.approval( id );
    }

    @GetMapping("getAllRegisterUser")
    public Result getAllRegisterUser() {
        return adminService.getAllRegisterUser();
    }

    @GetMapping("/exportAllByCompanyName")
    public void  exportAllEquipment(@RequestParam String companyName,
                                    HttpServletResponse response) throws Exception {
        log.info("批量导出 {} 全部设备" , companyName);
        List<Equipment> equipments = adminService.exportAllByCompanyName(companyName);
        ExcelUtil.exportEquipment(equipments, response);
    }

    @GetMapping("/getAllUser")
    public Result getAllUser() {
        return adminService.getAllUser();
    }


}



