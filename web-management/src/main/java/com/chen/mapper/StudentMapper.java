package com.chen.mapper;

import com.chen.pojo.Student;
import com.chen.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 根据班级查询学生
     */
    @Select("select count(*) from student where clazz_id = #{clazzId}")
    Integer selectByClazzId(Integer clazzId);

    /**
     * 分页查询,连接班级表查班级名称
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 添加学生
     */
    void insert(Student student);

    /**
     * 根据id查询学生
     */
    @Select("select * from student where id = #{id}")
    Student findById(Integer id);

    /**
     * 修改学生信息
     */
    void update(Student student);

    /**
     * 批量删除学生信息
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 统计班级人数
     */
    @MapKey("cls")
    List<Map<String, Object>> getStudentCountData();

    /**
     * 统计学历
     */
    @Select("select degree as name,count(*) value from student group by degree")
    List<Map<String, Object>> getStudentDegreeData();
}
