package com.chen.controller;

import com.chen.exception.ClazzException;
import com.chen.pojo.*;
import com.chen.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * 根据Id查询班级
     */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id){
        log.info("根据Id查询班级:{}",id);
        Clazz clazz=clazzService.getClazzById(id);
        return Result.success(clazz);
    }
    /**
     * 修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    /**
     * 根据id删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws ClazzException {
        log.info("根据id删除班级:{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }
    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result getAllClazz(){
        log.info("查询所有班级");
        List<Clazz> clazzes=clazzService.selectAll();
        return Result.success(clazzes);
    }
}
