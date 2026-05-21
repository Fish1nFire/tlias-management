package com.it.controller;

import com.it.pojo.Result;
import com.it.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 上传文件到阿里云OSS
     * @param file 上传的文件
     * @return 统一返回结果，包含文件访问URL
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        log.info("文件上传，文件名：{}", file.getOriginalFilename());
        
        try {
            //调用阿里云OSS工具类进行文件上传
            String url = aliyunOSSOperator.upload(file);
            log.info("文件上传成功，文件URL：{}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败，文件名：{}", file.getOriginalFilename(), e);
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }
}
