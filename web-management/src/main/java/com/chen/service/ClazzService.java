package com.chen.service;

import com.chen.pojo.Clazz;
import com.chen.pojo.ClazzQueryParam;
import com.chen.pojo.PageResult;

public interface ClazzService {
    /**
     * 分页查询
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 添加班级
     */
    void add(Clazz clazz);
}
