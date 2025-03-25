package com.chen.mapper;

import com.chen.pojo.Clazz;
import com.chen.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 分页查询
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 添加班级
     */
    void add(Clazz clazz);

    /**
     * 根据id查询班级
     */
    @Select("select * from clazz where id = #{id}")
    Clazz getClazzById(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);

    /**
     * 根据id删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 查询所有班级
     */
    @Select("select * from clazz")
    List<Clazz> selectAll();
}
