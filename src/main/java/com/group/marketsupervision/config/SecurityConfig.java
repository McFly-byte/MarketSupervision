package com.group.marketsupervision.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/admin/login", "/admin/register" ).permitAll() // 放行登录注册
//                        .anyRequest().authenticated() // 其他接口需认证
//                )
//                .csrf(csrf -> csrf.disable()); // 禁用CSRF保护
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // 所有请求都允许访问，无需认证
                )
                .csrf(csrf -> csrf.disable()); // 禁用CSRF保护
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}