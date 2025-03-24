package com.chen.service;

import com.chen.pojo.Emp;
import com.chen.pojo.EmpQueryParam;
import com.chen.pojo.PageResult;

public interface EmpService {
    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
