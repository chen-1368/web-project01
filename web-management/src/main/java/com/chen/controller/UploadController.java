package com.chen.controller;


import com.chen.pojo.Result;
import com.chen.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        log.info("文件上传OSS,url为：{}",url);
        return Result.success(url);
    }
}
