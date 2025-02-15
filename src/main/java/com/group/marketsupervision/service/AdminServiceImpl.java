package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.AdminMapper;
import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.util.JwtUtils;
import com.group.marketsupervision.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public LoginInfo login(String uname, String pwd) {
        Admin adminLogin = adminMapper.getAdminByUname(uname);
        // 是否有此用户
        if(adminLogin == null){
            return null;
        }
        // 验证密码
        if (!SecurityUtils.matchesPassword(pwd, adminLogin.getPwd())) {
            return null;
        }
        String jwt = JwtUtils.genJwt(adminLogin.getUname());
        LoginInfo loginInfo = new LoginInfo(adminLogin.getId(), adminLogin.getUname(), jwt);
        return loginInfo;
    }

    @Override
    public Result register(String uname, String pwd, String phone) {
        // 基础校验
        if (uname == null || uname.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        // 检查用户名重复
        Admin existingAdmin = adminMapper.getAdminByUname(uname);
        if (existingAdmin != null) {
            return Result.error("用户名已被占用");
        }
        if (pwd == null || pwd.length() < 8 || !pwd.matches(".*[A-Z].*") || !pwd.matches(".*[a-z].*")) {
            return Result.error("密码长度至少8位，需包含至少一个大、小写字母");
        }

        // 加密密码
        String encryptedPwd = SecurityUtils.encodePassword(pwd);

        Admin newAdmin = new Admin();
        newAdmin.setUname(uname);
        newAdmin.setPwd(encryptedPwd);
        newAdmin.setPhone(phone);
        newAdmin.setCreatedAt(LocalDateTime.now()); // 关键点：设置时间
        adminMapper.insertAdmin(newAdmin);

        String jwt = JwtUtils.genJwt(newAdmin.getUname());
        LoginInfo loginInfo = new LoginInfo(newAdmin.getId(), newAdmin.getUname(), jwt);

        return Result.success(loginInfo);
    }

}
