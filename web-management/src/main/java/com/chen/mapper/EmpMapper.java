package com.chen.mapper;

import com.chen.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp e left join dept d on d.id = e.dept_id")
    public Long count();

    /**
     * 查询一页员工信息和部门名称
     */
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
}
