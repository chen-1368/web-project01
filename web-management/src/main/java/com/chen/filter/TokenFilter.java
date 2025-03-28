package com.chen.filter;

import com.chen.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")// 拦截所有请求
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求的uri
        String uri = request.getRequestURI();
        // 如果包含“/login”，则放行
        if (uri.contains("/login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 如果token为空，则返回401
        if (token == null || token.isEmpty()) {
            log.info("token为空，返回401");
            response.setStatus(401);
            return;
        }
        // 判断token是否有效
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            // 如果token无效，则返回401
            log.info("token无效，返回401");
            response.setStatus(401);
            return;
        }
        // 如果token有效，则放行
        log.info("token有效，放行");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
