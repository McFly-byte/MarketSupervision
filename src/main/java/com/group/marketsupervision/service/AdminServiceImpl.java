package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.AdminMapper;
import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public LoginInfo login(String uname, String pwd) {
        Admin adminLogin = adminMapper.getAdminByUnameAndPwd(uname, pwd);
        if(adminLogin != null){
            LoginInfo loginInfo = new LoginInfo(adminLogin.getId(), adminLogin.getUname(), null);
            return loginInfo;
        }
        return null;
    }
}
