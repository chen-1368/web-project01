package com.chen.service.impl;

import com.chen.exception.DeptException;
import com.chen.mapper.DeptMapper;
import com.chen.mapper.EmpMapper;
import com.chen.pojo.Dept;
import com.chen.pojo.Emp;
import com.chen.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) throws DeptException {
        //判断该部门下是否有员工
        List<Emp> emp=empMapper.getByDeptId(id);
        if(emp.size()>0){
            throw new DeptException("对不起，当前部门下有员工，不能直接删除！对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getInfoById(Integer id) {
        return deptMapper.getInfoById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
