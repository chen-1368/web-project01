package com.chen.service;

import com.chen.exception.DeptException;
import com.chen.pojo.Dept;
import java.util.List;

public interface DeptService {
    /**
     * 查询所有的部门数据
     */
    List<Dept> findAll();

    /**
     * 按id删除部门
     */
    void deleteById(Integer id) throws DeptException;

    /**
     * 添加部门
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     *
     * @return
     */
    Dept getInfoById(Integer id);

    /**
     * 修改部门
     */
    void update(Dept dept);
}
