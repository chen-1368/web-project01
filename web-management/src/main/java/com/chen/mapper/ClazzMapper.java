package com.chen.mapper;

import com.chen.pojo.Clazz;
import com.chen.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;
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
}
