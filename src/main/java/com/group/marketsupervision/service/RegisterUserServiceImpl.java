package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.RegisterUserMapper;
import com.group.marketsupervision.pojo.RegisterUser;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserMapper registerUserMapper;

    @Override
    public Result register(RegisterUser registerUser) {
        String uname = registerUser.getUsername();
        String pwd = registerUser.getPassword();
        if (uname == null || uname.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        RegisterUser existingRegisterUser = registerUserMapper.getRegisterUserByUsername(uname);
        if (existingRegisterUser != null) {
            return Result.error("用户名已存在，请勿重复申请");
        }
        if (pwd == null || pwd.length() < 8 || !pwd.matches(".*[A-Z].*") || !pwd.matches(".*[a-z].*")) {
            return Result.error("密码长度至少8位，需包含至少一个大、小写字母");
        }
        String encryptedPwd = SecurityUtils.encodePassword(pwd);
        registerUser.setPassword(encryptedPwd);
        registerUser.setCreatedTime(LocalDateTime.now());
        registerUserMapper.insertRegisterUser(registerUser);
        return Result.success("申请成功，请等待审核");
    }
}
