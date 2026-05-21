package com.it.service.impl;

import com.it.mapper.DeptMapper;
import com.it.pojo.Dept;
import com.it.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    //查询所有部门信息
    @Override
    public List<Dept> findall() {
        return deptMapper.findall();
    }
    //根据id删除部门信息
    @Override
    public void deleteByid(Integer id) {
        deptMapper.deleteByid(id);
    }

    @Override
    public void addDept(Dept dept) {
        //1.添加基础属性，创建时间和更新时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层方法执行添加
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1.添加基础属性，更新时间
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层方法执行修改
        deptMapper.update(dept);
    }
}
