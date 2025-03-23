package com.chen.service.impl;

import com.chen.mapper.EmpMapper;
import com.chen.pojo.Emp;
import com.chen.pojo.PageResult;
import com.chen.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name,
                                Integer gender, LocalDate begin, LocalDate end) {
        //1. 设置分页参数（page，pageSize）
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<Emp> list=empMapper.list(name,gender,begin,end);

        //3. 解析查询结果，并封装
        PageInfo<Emp> pageInfo=new PageInfo<>(list);
        return new PageResult<Emp>(pageInfo.getTotal(),pageInfo.getList());
    }
}
