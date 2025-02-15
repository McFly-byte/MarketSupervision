package com.group.marketsupervision;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.group.marketsupervision.mapper.AdminMapper;
import com.group.marketsupervision.pojo.Admin;
import com.group.marketsupervision.pojo.LoginInfo;
import com.group.marketsupervision.pojo.Result;
import com.group.marketsupervision.service.AdminService;
import com.group.marketsupervision.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.security.KeyRep.Type.SECRET;


@SpringBootTest
class MarketSupervisionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    void testGetAdminList(){
        System.out.println(adminMapper.getAdminList());
    }

   @Test
    void testGenJwt() {
       long Time=30*60*1000; //设置jwt令牌有效时间为30分钟
       String SECRET= "wwww";  //设置jwt令牌的密钥，自定义
       String name = "test";
       String token;
       try {
           Date d = new Date(System.currentTimeMillis() + Time); //时间格式转换，Time为上面你自己设置的时间
           Algorithm algorithm = Algorithm.HMAC256(SECRET);   //调用Algorithm的HMAC256()方法，将密钥通过该算法加密
           Map<String, Object> map = new HashMap<String, Object>(2);  //使用map集合存储jwt令牌的头部信息
           map.put("Type", "jwt");
           map.put("alg", "HS256");
           String t = JWT.create().
                   withHeader(map).
                   withClaim("name", name). //写入你要存储在令牌中的信息
                           withExpiresAt(d).
                   sign(algorithm);   //生成令牌
           token = t;
           System.out.println(t);
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println(e);
       }
   }

   @Test
    void testVerifyJwt() {
           long Time=30*60*1000; //设置jwt令牌有效时间为30分钟
           String SECRET= "wwww";  //设置jwt令牌的密钥，自定义
           String token = "1234";
           try {
               Algorithm algorithm = Algorithm.HMAC256(SECRET);  //调用Algorithm的HMAC256()方法，将密钥通过该算法加密
               JWTVerifier verifier = JWT.require(algorithm).build();   //验证密钥是否正确
               DecodedJWT jwt = verifier.verify(token);      //验证jwt令牌是否正确
               System.out.println(1);
           }catch (Exception e){
               e.printStackTrace();
               System.out.println(0);
           }
    }

    @Test
    void testToken() {
        String name = "test";
        String token = JwtUtils.genJwt(name);
        System.out.println(token);
        if( JwtUtils.verify(token) ) {
            System.out.println("verifying success");
        }
        else {
            System.out.println("fail");
        }
        System.out.println(JwtUtils.claim(token));
    }

    @Test
    void testLogin() {
        String uname = "test";
        String pwd = "0000";
        LoginInfo loginInfo = adminService.login(uname, pwd);
        if(loginInfo != null){
            System.out.println("登录成功");
        }
        else {
            System.out.println("用户名或密码错误~");
        }

    }

}
