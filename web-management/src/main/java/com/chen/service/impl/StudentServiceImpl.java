package com.chen.service.impl;

import com.chen.mapper.StudentMapper;
import com.chen.pojo.PageResult;
import com.chen.pojo.Student;
import com.chen.pojo.StudentQueryParam;
import com.chen.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //查询学生信息
        List<Student> students=studentMapper.page(studentQueryParam);
        //解析数据并封装
        PageInfo<Student> pageInfo=new PageInfo<>(students);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void insert(Student student) {
        //完善信息
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //添加到数据库
        studentMapper.insert(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void update(Student student) {
        //更新时间
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        studentMapper.deleteBatch(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void violation(Integer id, Integer score) {
        //查询该学生信息
        Student student=studentMapper.findById(id);
        //更新学生信息
        student.setViolationCount((short)(student.getViolationCount()+1));
        student.setViolationScore((short)(student.getViolationScore()+score));
        //更新时间
        student.setUpdateTime(LocalDateTime.now());
        //修改学生信息
        studentMapper.update(student);
    }
}
