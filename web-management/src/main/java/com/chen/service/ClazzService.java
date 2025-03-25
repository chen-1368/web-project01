package com.chen.service;

import com.chen.exception.ClazzException;
import com.chen.pojo.Clazz;
import com.chen.pojo.ClazzQueryParam;
import com.chen.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 添加班级
     */
    void add(Clazz clazz);

    /**
     * 根据Id查询班级
     */
    Clazz getClazzById(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);

    /**
     * 根据id删除班级
     */
    void deleteById(Integer id) throws ClazzException;

    /**
     * 查询所有班级
     */
    List<Clazz> selectAll();
}
