package com.it.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.it.mapper.ClazzMapper;
import com.it.pojo.Clazz;
import com.it.pojo.ClazzQueryParam;
import com.it.pojo.PageResult;
import com.it.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 条件分页查询班级列表
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 2. 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 3. 封装结果并返回
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 删除班级信息
     */
    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    /**
     * 添加班级信息
     */
    @Override
    public void addClazz(Clazz clazz) {
        // 1. 设置创建时间和更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        // 2. 调用mapper层方法执行添加
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID查询班级信息
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }
}
