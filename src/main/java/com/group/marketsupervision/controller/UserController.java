package com.group.marketsupervision.controller;

import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.pojo.User;
import com.group.marketsupervision.service.UserService;
import com.group.marketsupervision.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173") // todo 后面改成服务器前端地址
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录 , {} {}", user.getUsername(), user.getPassword());
        return userService.login(user);
    }

    @PostMapping("/addEquip")
    public Result addEquip( @RequestBody Equipment equipment) {
        log.info("用户添加设备 , {}", equipment);
        return userService.insertEquipment(equipment);
    }

    @PostMapping("/parseExcel")
    public Result parseExcel(@RequestParam("file") MultipartFile file) {
        try {
            log.info("解析用户上传文件 , {}", file.getOriginalFilename());
            List<Equipment> equipments = ExcelUtil.parse(file);
            log.info("解析结果 , {}", equipments);
            return Result.success(equipments);
        } catch (Exception e) {
            e.printStackTrace();
        return Result.error("上传失败，请检查文件格式");
        }
    }

    @PostMapping("/importEquipments")
    public Result importEquipments(@RequestBody List<Equipment> equipments) {
        log.info("导入设备列表 , {}", equipments);
        return userService.importEquipments(equipments);
    }

    @DeleteMapping("/deleteEquipmentById")
    public Result deleteEquipmentById(@RequestBody Equipment equipment) {
        Integer id = equipment.getId();
        log.info("删除设备 , {}", id);
        return userService.deleteEquipmentById(id);
    }

    @GetMapping("/getAllEquipments")
    public Result getAllEquipments( @RequestParam Integer id) {
        log.info("获取用户绑定企业的所有设备 , 用户id：{}", id);
        return userService.getAllEquipments(id);
    }

}
