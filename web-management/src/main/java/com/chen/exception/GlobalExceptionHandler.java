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
}
