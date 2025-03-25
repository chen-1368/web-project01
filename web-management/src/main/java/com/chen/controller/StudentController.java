package com.chen.controller;

import com.chen.pojo.PageResult;
import com.chen.pojo.Result;
import com.chen.pojo.Student;
import com.chen.pojo.StudentQueryParam;
import com.chen.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询:{}", studentQueryParam);
        PageResult<Student> pageResult=studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }
    /**
     * 添加学生
     */
    @PostMapping
    public Result insert(@RequestBody Student student){
        log.info("添加学生：{}",student);
        studentService.insert(student);
        return Result.success();
    }
    /**
     * 根据id查询学生
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询学生：{}",id);
        Student student=studentService.findById(id);
        return Result.success(student);
    }
    /**
     * 修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息：{}",student);
        studentService.update(student);
        return Result.success();
    }
    /**
     * 批量删除学生
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学生:{}",ids);
        studentService.deleteBatch(ids);
        return Result.success();
    }
    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理:{},{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
