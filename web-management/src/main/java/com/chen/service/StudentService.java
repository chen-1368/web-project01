package com.chen.service;

import com.chen.pojo.PageResult;
import com.chen.pojo.Student;
import com.chen.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 添加学生
     */
    void insert(Student student);

    /**
     * 根据id查询学生
     */
    Student findById(Integer id);

    /**
     * 修改学生信息
     */
    void update(Student student);

    /**
     * 批量删除学生
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 违纪扣分
     * @param id 学生id
     * @param score 要扣的分数
     */
    void violation(Integer id, Integer score);
}
