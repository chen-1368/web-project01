package com.chen.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    Integer page=1;
    Integer pageSize=10;
    String name;//学生姓名
    Integer degree;//学历
    Integer clazzId;//班级
}
