package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.CompanyMapper;
import com.group.marketsupervision.mapper.EquipmentMapper;
import com.group.marketsupervision.pojo.Company;
import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.util.JwtUtils;
import com.group.marketsupervision.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComServiceImpl implements ComService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public LoginInfo login(String cname, String pwd) {
        Company companyLogin = companyMapper.getCompanyByCname(cname);
        // todo 因为是通过cname检索，所以cname一定要唯一。 在uc注册时还要到company表查重

        if(companyLogin == null){
            return null;
        }

        if (!SecurityUtils.matchesPassword(pwd, companyLogin.getPwd())) {
            return null;
        }
        String jwt = JwtUtils.genJwt(companyLogin.getCname());
        LoginInfo loginInfo = new LoginInfo(companyLogin.getId(), companyLogin.getCname(), jwt);
        return loginInfo;
    }

    @Override
    public Result insertEquipment(Equipment equipment) {
        equipment.setCreatedAt(LocalDateTime.now());
        equipmentMapper.insertEquipment(equipment);
        return Result.success(equipment);
    }
}
