package com.chen.service.impl;

import com.chen.mapper.ClazzMapper;
import com.chen.pojo.Clazz;
import com.chen.pojo.ClazzQueryParam;
import com.chen.pojo.PageResult;
import com.chen.service.ClazzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        // 执行查询
        List<Clazz> list=clazzMapper.list(clazzQueryParam);
        // 更新状态
        list.forEach(e->{
            if(LocalDate.now().isBefore(e.getBeginDate()))
                e.setStatus("未开班");
            else if (LocalDate.now().isBefore(e.getEndDate()))
                e.setStatus("在读中");
            else
                e.setStatus("已结课");
        });
        // 解析查询结果，并封装
        PageInfo<Clazz> pageInfo=new PageInfo<>(list);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void add(Clazz clazz) {
        //补全信息
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }
}
