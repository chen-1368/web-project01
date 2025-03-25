package com.chen.service;

import com.chen.pojo.ClazzOption;
import com.chen.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计职位人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 统计班级人数
     */
    ClazzOption getStudentCountData();

    /**
     * 统计学历
     */
    List<Map<String, Object>> getStudentDegreeData();
}
