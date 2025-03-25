package com.chen.service.impl;

import com.chen.mapper.EmpMapper;
import com.chen.mapper.StudentMapper;
import com.chen.pojo.ClazzOption;
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
    @Autowired
    StudentMapper studentMapper;

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

    @Override
    public ClazzOption getStudentCountData() {
        // 调用统计接口
        List<Map<String, Object>> list = studentMapper.getStudentCountData();
        //封装成ClazzOption
        List<Object>  cls = list.stream().map(dataMap -> dataMap.get("cls")).toList();
        List<Object> num = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzOption(cls,num);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> studentDegreeData = studentMapper.getStudentDegreeData();
        //处理数据
        studentDegreeData.forEach(data->{
            switch ((Integer) data.get("name")){
                case 1: data.put("name","初中");break;
                case 2: data.put("name","高中");break;
                case 3: data.put("name","大专");break;
                case 4: data.put("name","本科");break;
                case 5: data.put("name","硕士");break;
                case 6: data.put("name","博士");break;
            }
        });
        return studentDegreeData;
    }
}
