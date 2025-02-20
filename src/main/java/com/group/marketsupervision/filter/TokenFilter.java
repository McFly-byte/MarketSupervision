//package com.group.marketsupervision.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import lombok.extern.slf4j.Slf4j;
//import com.group.marketsupervision.util.JwtUtils;
//
//import java.io.IOException;
//

// * 令牌校验过滤器
// * todo 未测试
// */
//@Slf4j
//@WebFilter(urlPatterns = "/*")
//public class TokenFilter implements Filter {
//
//    private static final String[] EXCLUDE_PATHS = {"/admin/login", "/admin/register", "/user/login", "/registrerUser/register"};
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // 检查是否在排除路径中
//        if (isExcludedPath(httpRequest.getRequestURI())) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 从Header获取Authorization
//        String authHeader = httpRequest.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            sendUnauthorizedError(httpResponse, "Missing or invalid Authorization header");
//            return;
//        }
//
//        // 提取Token
//        String token = authHeader.substring(7);
//
//        // 验证Token有效性
//        if (!JwtUtils.verify(token)) {
//            sendUnauthorizedError(httpResponse, "Invalid or expired token");
//            return;
//        }
//
//        // 获取用户名并存入请求属性
//        String username = JwtUtils.claim(token);
//        if (username == null) {
//            sendUnauthorizedError(httpResponse, "Invalid token content");
//            return;
//        }
//
//        httpRequest.setAttribute("currentUser", username);
//        chain.doFilter(request, response);
//    }
//
//    private boolean isExcludedPath(String requestURI) {
//        for (String path : EXCLUDE_PATHS) {
//            if (requestURI.startsWith(path)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void sendUnauthorizedError(HttpServletResponse response, String message) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write("{\"code\": 401, \"message\": \"" + message + "\"}");
//        response.getWriter().flush();
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {}
//
//    @Override
//    public void destroy() {}
//
//
//}