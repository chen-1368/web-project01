package com.chen.service.impl;

import com.chen.mapper.EmpMapper;
import com.chen.pojo.JobOption;
import com.chen.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        //1. 调用统计的接口
        List<Map<String, Object>> list = empMapper.getAllEmpJobCount();
        //2. 封装成JobOption对象
        List<Object> pos = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> num = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(pos,num);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }
}
