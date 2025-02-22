package com.group.marketsupervision.controller;

import com.group.marketsupervision.mapper.EquipmentMapper;
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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173") // todo 后面改成服务器前端地址
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private EquipmentMapper equipmentMapper;

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
    public Result reject(@RequestBody Map<String, Integer> request ) { // 企业用户id
        Integer id = request.get("id"); // 从 JSON 对象中提取 id
        log.info("审核企业用户-拒绝（id：{}）", id);
        return adminService.reject( id );
    }

    @PostMapping("approval")
    public Result approval (@RequestBody Map<String, Integer> request ) { // 企业用户id
        Integer id = request.get("id"); // 从 JSON 对象中提取 id
        log.info("审核企业用户-通过（id：{}）", id);
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

    @GetMapping("/getAllEquipments")
    public Result getAllEquipments( @RequestBody Map<String, String> request) {
        String companyName = request.get("companyName");
        log.info("按企业名称查找全部设备，{}" , companyName);
        return adminService.getEquipmentsByCompanyName(companyName);
    }

    @DeleteMapping("/deleteEquipment")
    public Result deleteEquipment(@RequestBody Equipment equipment) {
        Integer id = equipment.getId();
        log.info("删除设备 , {}", id);
        return adminService.deleteEquipmentById(id);
    }

    @GetMapping("/getAllOverdueEquips")
    public Result getAllOverdueEquips() {
        log.info("获取全部逾期设备");
        return Result.success(adminService.getAllOverdueEquips());
    }

    @GetMapping("/getOverdueEquipsByCompanyName")
    public Result getOverdueEquipmentsByCompanyName(@RequestBody Map<String, String> request) {
        String companyName = request.get("companyName");
        log.info("按企业名称查找逾期设备，{}" , companyName);
        return Result.success(adminService.getOverdueEquipmentsByCompanyName(companyName));
    }

    @GetMapping("/getAllWillOverdueEquips")
    public Result getAllWillOverdueEquips() {
        log.info("获取全部即将逾期设备");
        return Result.success(adminService.getAllWillOverdueEquips());
    }

    @GetMapping("/getWillOverdueEquipsByCompanyName")
    public Result getWillOverdueEquipsByCompanyName(@RequestBody Map<String, String> request) {
        String companyName = request.get("companyName");
        log.info("按企业名称查找即将逾期设备，{}" , companyName);
        return Result.success(adminService.getWillOverdueEquipsByCompanyName(companyName));
    }



}



