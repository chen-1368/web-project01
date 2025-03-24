package com.chen.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page=1;//页码
    private Integer pageSize=10;//每页展示的记录数
    private String name;//姓名
    private  Integer gender;//性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//入职的开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//入职的结束时间
}
