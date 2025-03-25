package com.chen.controller;

import com.chen.pojo.*;
import com.chen.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    /**
     * 添加班级
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加一个班级：{}",clazz);
        clazzService.add(clazz);
        return Result.success();
    }
}
