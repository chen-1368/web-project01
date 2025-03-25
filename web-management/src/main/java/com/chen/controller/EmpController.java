package com.chen.controller;

import com.chen.pojo.Emp;
import com.chen.pojo.EmpQueryParam;
import com.chen.pojo.PageResult;
import com.chen.pojo.Result;
import com.chen.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工表的controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    /**
     * 保存员工信息，包含工作经历
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("保存的员工信息：{}",emp);
        empService.save(emp);
        return Result.success();
    }
    /**
     * 删除员工信息,并删除其工作经历
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("要删除的员工的Id：{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("根据id查询员工信息：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
