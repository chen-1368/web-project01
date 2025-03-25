package com.chen.service;

import com.chen.pojo.Emp;
import com.chen.pojo.EmpQueryParam;
import com.chen.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void save(Emp emp);

    /**
     * 删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getById(Integer id);

    /**
     * 更新员工
     */
    void update(Emp emp);

    /**
     * 查询所有员工
     */
    List<Emp> getAllEmp();
}
