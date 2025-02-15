package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.AdminMapper;
import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public LoginInfo login(String uname, String pwd) {
        Admin adminLogin = adminMapper.getAdminByUnameAndPwd(uname, pwd);
        if(adminLogin != null){
            String jwt = JwtUtils.genJwt(adminLogin.getUname());
            LoginInfo loginInfo = new LoginInfo(adminLogin.getId(), adminLogin.getUname(), jwt);
            return loginInfo;
        }
        return null;
    }
}
