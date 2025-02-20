package com.group.marketsupervision.controller;

import com.group.marketsupervision.pojo.RegisterUser;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.service.RegisterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/registrerUser")
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterUser registerUser) {
        log.info("企业用户注册：{} {} {}", registerUser.getUsername(), registerUser.getPassword(), registerUser.getCompanyName());
        return registerUserService.register(registerUser);
    }
}