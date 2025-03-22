package com.chen.controller;

import com.chen.pojo.Dept;
import com.chen.pojo.Result;
import com.chen.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        System.out.println("查询所有部门");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(Integer id){
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        System.out.println("需要查询的部门id是："+id);
        Dept dept=deptService.getInfoById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("修改的部门："+dept);
        deptService.update(dept);
        return Result.success();
    }
}
