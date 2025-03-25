package com.chen.controller;

import com.chen.exception.DeptException;
import com.chen.pojo.Dept;
import com.chen.pojo.Result;
import com.chen.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询所有部门");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(Integer id) throws DeptException {
        log.info("根据id删除数据，id为：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加一个部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据id查询部门,id是: {}",id);
        Dept dept=deptService.getInfoById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改的部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
