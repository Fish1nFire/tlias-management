package com.it.controller;

import com.it.pojo.Clazz;
import com.it.pojo.ClazzQueryParam;
import com.it.pojo.PageResult;
import com.it.pojo.Result;
import com.it.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 班级管理Controller
 */
@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 条件分页查询班级列表
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级列表，请求参数：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 删除班级信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级信息，班级ID：{}", id);
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 添加班级信息
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级信息，班级信息：{}", clazz);
        clazzService.addClazz(clazz);
        // 添加成功后，根据ID查询完整的班级信息（包含班主任姓名）
        Clazz result = clazzService.getById(clazz.getId());
        return Result.success(result);
    }

    /**
     * 根据ID查询班级信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询班级信息，班级ID：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
}
