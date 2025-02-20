package com.group.marketsupervision;

import com.group.marketsupervision.mapper.AdminMapper;
import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.Equipment;
import com.group.marketsupervision.pojo.RegisterUser;
import com.group.marketsupervision.pojo.User;
import com.group.marketsupervision.service.AdminService;
import com.group.marketsupervision.service.RegisterUserService;
import com.group.marketsupervision.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class MarketSupervisionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private UserService userService;

    @Test
    void testAdminRegister() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("Aa1234567");
        admin.setPhone("12345678901");
//        admin.setCreatedAt(LocalDateTime.now());
        adminService.register(admin);
    }

    @Test
    void testAdminLogin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("Aa1234567");
        System.out.println(adminService.login(admin));
    }

    @Test
    void testRegisterUserRegister() {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername("register_user");
        registerUser.setPassword("Aa1234567");
        registerUser.setCompanyName("company");
        registerUser.setRegion("region");
        System.out.println(registerUserService.register(registerUser));
    }

    @Test
    void testUserLogin() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("Aa1234567");
        System.out.println(userService.login(user));
    }

    @Test
    void testGetAllRegisterUser() {
        System.out.println(adminService.getAllRegisterUser());
    }

    @Test
    void testApproval() {
        System.out.println(adminService.approval(1000016));
    }

    @Test
    void testInsertEquipment() {
        Equipment equipment = new Equipment();
        equipment.setEname("equipment");
        equipment.setEcode("code");
        equipment.setRegistrationNumber("number");
        equipment.setVersion("version");
        System.out.println(userService.insertEquipment(equipment));
    }

    @Test
    void testExpotEquipment() {
        System.out.println(adminService.exportAllByCompanyName("companyName"));
    }
}
