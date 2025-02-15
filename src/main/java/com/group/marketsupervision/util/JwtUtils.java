package com.group.marketsupervision.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    public static long Time = 30 * 60 * 1000; // 设置jwt令牌有效时间为30分钟
    public static String SECRET = "marketsupervision";  // 设置jwt令牌的密钥，自定义

    public static String genJwt(String name) {
        String token;
        try {
            Date d = new Date(System.currentTimeMillis() + Time);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withClaim("uname", name) // 写入你要存储在令牌中的信息
                    .withExpiresAt(d)
                    .sign(algorithm);   // 生成令牌
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verify(String token) {  // token为生成的jwt令牌
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();   // 验证密钥是否正确
            verifier.verify(token);      // 验证jwt令牌是否正确
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String claim(String token) {  // token为生成的jwt令牌
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); // 验证密钥是否正确
            DecodedJWT jwt = verifier.verify(token);     // 验证jwt令牌是否正确
            return jwt.getClaim("uname").asString();     // 获取jwt令牌中的信息，并以String类型返回
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}