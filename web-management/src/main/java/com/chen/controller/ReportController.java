package com.chen.controller;

import com.chen.pojo.ClazzOption;
import com.chen.pojo.JobOption;
import com.chen.pojo.Result;
import com.chen.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    ReportService reportService;
    /**
     * 统计职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计职位人数");
        JobOption jobOption=reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    /**
     * 统计员工性别人数
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /**
     * 统计班级人数
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }
    /**
     * 统计学历
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学历");
        List<Map<String,Object>> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }
}
