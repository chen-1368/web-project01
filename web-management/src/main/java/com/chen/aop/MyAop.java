package com.chen.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAop {
    @Pointcut("execution(* com.chen.service.impl.*.*(..))")
    public void pc(){}

    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp){
        String methodName = pjp.getSignature().getName();
        log.info("方法执行前：{}", methodName);

        Object result = null; // 执行目标方法
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.info("方法执行后：{}", methodName);
        return result;
    }
}
