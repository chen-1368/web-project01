package com.chen.interceptor;

import com.chen.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 如果token为空，则返回401
        if (token == null || token.isEmpty()) {
            log.info("token为空，返回401");
            response.setStatus(401);
            return false;
        }
        // 判断token是否有效
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            // 如果token无效，则返回401
            log.info("token无效，返回401");
            response.setStatus(401);
            return false;
        }
        // 如果token有效，则放行
        log.info("token有效，放行");
        return true;
    }
}
