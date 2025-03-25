package com.chen.exception;

import com.chen.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("出错了",e);
        return Result.error("出错了，请联系管理员。");
    }

    @ExceptionHandler
    public Result handelDuplicateKeyException(DuplicateKeyException e){
        log.error("出错了",e);
        String msg = e.getMessage();
        String info = msg.substring(msg.indexOf("Duplicate entry")).split(" ")[2];
        return Result.error(info+"已存在");
    }

    @ExceptionHandler
    public Result handelClazzException(ClazzException e){
        log.error("对不起, 该班级下有学生, 不能直接删除");
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result handelDeptException(DeptException e){
        log.error("对不起，当前部门下有员工，不能直接删除！");
        return Result.error(e.getMessage());
    }
}
