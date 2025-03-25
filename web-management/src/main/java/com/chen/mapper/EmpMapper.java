package com.chen.mapper;

import com.chen.pojo.Emp;
import com.chen.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询员工信息和部门名称
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工信息
     */
    void updateById(Emp emp);

    /**
     * 查询职位并统计人数
     */
    @MapKey("pos")
    List<Map<String, Object>> getAllEmpJobCount();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 查询所以员工
     */
    @Select("select * from emp")
    List<Emp> getAllEmp();
}
