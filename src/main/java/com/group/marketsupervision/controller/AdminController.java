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
    public Result approvalUC (@RequestBody Integer id ) { // 企业用户id
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

    @GetMapping("getUserByName")
    //从前端获取一个name，从数据库中查询这个用户，如果有则返回其信息
    public Result getUserByName(
            @RequestParam("username") String username
    ) {
        return adminService.getUserByName(username);
    }

    //查看所有企业用户，是已经注册的
    @GetMapping("getAllUser")
    public Result getAllUser() {
        return adminService.getAllUser();
    }

    @GetMapping("getEquipById")
    public Result getEquipById(
            @RequestParam("id") String id
    ) {
        return adminService.getEquipById(id);
    }

    @GetMapping("getAllEquipWillOverdue")
    public Result getAllEquipWillOverdue() {
        return adminService.getAllEquipWillOverdue();
    }

    @GetMapping("getEquipWillExpireByUsername")
    public Result getEquipWillExpireByUsername(
            @RequestParam("username") String username
    ) {
        return adminService.getEquipWillExpireByUsername(username);
    }

    @GetMapping("getAllOverdueEquips")
    public Result getAllOverdueEquips() {
        return adminService.getAllOverdueEquips();
    }

    @GetMapping("getExpiredEquipsById")
    public Result getExpiredEquipsById(
            @RequestParam("id") String id
    ){
        return adminService.getExpiredEquipsById(id);
    }




}



