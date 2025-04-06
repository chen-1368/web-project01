package com.chen.aop;


import com.chen.mapper.OperateLogMapper;
import com.chen.pojo.OperateLog;
import com.chen.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
//@Aspect
//@Component
public class OperationLogAspect {
//    @Autowired
    private OperateLogMapper operateLogMapper;

    @Pointcut("execution(* com.chen.controller.EmpController.*(..))")
    public void pc() {}

    @Around("pc()")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable {
        log.info("进入切面");
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = pjp.proceed();

        // 记录耗时
        long costTime = System.currentTimeMillis() - startTime;

        // 创建操作日志对象
        OperateLog operateLog = new OperateLog();

        // 设置操作日志信息
        operateLog.setOperateEmpId(getCurrentEmpId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(pjp.getTarget().getClass().getName());
        operateLog.setMethodName(pjp.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        operateLog.setReturnValue(result != null ? "many" : "void");
        operateLog.setCostTime(costTime);

        // 保存操作日志
        log.info("操作日志：{}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }
    private Integer getCurrentEmpId() {
        return CurrentHolder.getCurrentId();
    }
}
