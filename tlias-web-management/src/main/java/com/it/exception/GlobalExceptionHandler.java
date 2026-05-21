package com.it.exception;

import com.it.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @RestControllerAdvice 注解表示这是一个控制器的建议类，用于处理全局异常
 * @ExceptionHandler 注解表示处理指定异常类型
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("异常信息：{}", e.getMessage());
        return Result.error("操作失败");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("异常信息：{}", e.getMessage());
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errorMsg = message.substring(i);
        String[] split = errorMsg.split(" ");
        return Result.error("【" + split[2] + "】已存在");
    }
}
