package com.chen.service.impl;

import com.chen.mapper.EmpExprMapper;
import com.chen.mapper.EmpMapper;
import com.chen.pojo.Emp;
import com.chen.pojo.EmpExpr;
import com.chen.pojo.EmpQueryParam;
import com.chen.pojo.PageResult;
import com.chen.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
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

    @Transactional(rollbackFor = {Exception.class})//出现任何异常都要回滚
    @Override
    public void save(Emp emp) {
        //1. 完善员工信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //2. 保存员工信息
        empMapper.insert(emp);
        //3. 保存工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //为工作经历的empId赋值
            exprList.forEach(e->e.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1. 删除员工信息
        empMapper.deleteByIds(ids);

        //2. 删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 更新员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 删除员工所有的工作经历
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
        //3. 再添加员工的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(e->e.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
