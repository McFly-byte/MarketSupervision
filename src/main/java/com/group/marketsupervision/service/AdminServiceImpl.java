package com.group.marketsupervision.service;

import com.group.marketsupervision.mapper.AdminMapper;
import com.group.marketsupervision.mapper.EquipmentMapper;
import com.group.marketsupervision.mapper.RegisterUserMapper;
import com.group.marketsupervision.mapper.UserMapper;
import com.group.marketsupervision.pojo.*;
import com.group.marketsupervision.util.JwtUtils;
import com.group.marketsupervision.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RegisterUserMapper registerUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Result login(Admin admin) {
        Admin adminLogin = adminMapper.getAdminByUname(admin.getUsername());
        // 是否有此用户
        if(adminLogin == null){
            return Result.error("用户不存在");
        }
        // 验证密码
        if (!SecurityUtils.matchesPassword(admin.getPassword(), adminLogin.getPassword())) {
            return Result.error("密码错误");
        }
        String jwt = JwtUtils.genJwt(adminLogin.getUsername());
        LoginInfo loginInfo = new LoginInfo(adminLogin.getId(), adminLogin.getUsername(), jwt);
        return Result.success(loginInfo);
    }

    @Override
    public Result register(Admin admin) {
        String uname = admin.getUsername();
        String pwd = admin.getPassword();
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

        admin.setPassword(encryptedPwd);
        admin.setCreatedTime(LocalDateTime.now()); // 关键点：设置时间
        adminMapper.insertAdmin(admin);

        String jwt = JwtUtils.genJwt(uname);
        LoginInfo loginInfo = new LoginInfo(admin.getId(), uname, jwt);

        return Result.success(loginInfo);
    }

    // todo 这里的业务逻辑还需讨论
    @Override
    public Result reject(Integer id ) {
        return Result.success("请联系管理员");
    }

    // fixme
    @Override
    public Result approval( Integer id ) {
        try {
            RegisterUser registerUser = registerUserMapper.getRegisterUserById(id);
            User user = new User();
            user.setUsername(registerUser.getUsername());
            user.setPassword(registerUser.getPassword());
            user.setCompanyName(registerUser.getCompanyName());
            user.setRegion(registerUser.getRegion());
            user.setCreatedTime(LocalDateTime.now());
            userMapper.insert(user);
            registerUserMapper.deleteById(id);
            return Result.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("审核失败");
        }
    }


    @Override
    public Result getAllRegisterUser() {
        List<RegisterUser> uclist = registerUserMapper.getAllRegisterUser();
        if ( uclist == null ) return Result.error("没有待审核用户");
        return Result.success(uclist);
    }

    @Override
    public List<Equipment> exportAllByCompanyName(String companyName) {
        return equipmentMapper.getEquipmentsByCompanyName(companyName);
    }

    @Override
    public Result getAllUser() {
        List<User> users = userMapper.getAllUser();
        if (users == null) return Result.error("没有用户");
        return Result.success(users);
    }

}
