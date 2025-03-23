package com.chen.service;

import com.chen.pojo.Emp;
import com.chen.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {
    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页查询的记录数
     * @return
     */
    PageResult<Emp> page(Integer page, Integer pageSize, String name,
                         Integer gender, LocalDate begin, LocalDate end);
}
