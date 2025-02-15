package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.UCMapper;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.pojo.UnverifiedCom;
import com.group.marketsupervision.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UCServiceImpl implements UCService {

    @Autowired
    private UCMapper ucMapper;

    @Override
    public Result register(String ucname, String pwd, String phone) {
        if (ucname == null || ucname.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }

        UnverifiedCom existinguc = ucMapper.getUcByUCName(ucname);
        if (existinguc != null) {
            return Result.error("用户名已存在，请勿重复申请");
        }

        if (pwd == null || pwd.length() < 8 || !pwd.matches(".*[A-Z].*") || !pwd.matches(".*[a-z].*")) {
            return Result.error("密码长度至少8位，需包含至少一个大、小写字母");
        }
        String encryptedPwd = SecurityUtils.encodePassword(pwd);
        UnverifiedCom newUC = new UnverifiedCom();
        newUC.setCname(ucname);
        newUC.setPwd(encryptedPwd);
        newUC.setPhone(phone);
        newUC.setCreatedAt(LocalDateTime.now());
        newUC.setComment("");
        ucMapper.insertUC(newUC);
        return Result.success("申请成功，请等待审核");
    }

}