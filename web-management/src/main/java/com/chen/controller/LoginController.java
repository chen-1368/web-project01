package com.chen.controller;

import com.chen.pojo.Emp;
import com.chen.pojo.LoginInfo;
import com.chen.pojo.Result;
import com.chen.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录请求");
        LoginInfo info=empService.login(emp);
        if(info!=null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}
