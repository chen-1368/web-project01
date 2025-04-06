package com.chen.interceptor;

import com.chen.utils.CurrentHolder;
import com.chen.utils.JwtUtils;
import io.jsonwebtoken.Claims;
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
            Claims claims = JwtUtils.parseToken(token);
            // 把id记录到当前线程中
            Integer empId = claims.get("id", Integer.class);
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录的员工id是：{},将其存入ThreadLocal",empId);
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

    /**
     * 在调用完controller层的方法后，移除当前线程中的数据
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除ThreadLocal中的数据
        log.info("移除当前线程中的数据");
        CurrentHolder.remove();
    }
}
