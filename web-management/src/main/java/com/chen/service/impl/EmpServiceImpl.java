package com.chen.service.impl;

import com.chen.mapper.EmpMapper;
import com.chen.pojo.Emp;
import com.chen.pojo.EmpQueryParam;
import com.chen.pojo.PageResult;
import com.chen.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        //2. 执行查询
        List<Emp> list=empMapper.list(empQueryParam);

        //3. 解析查询结果，并封装
        PageInfo<Emp> pageInfo=new PageInfo<>(list);
        return new PageResult<Emp>(pageInfo.getTotal(),pageInfo.getList());
    }
}
