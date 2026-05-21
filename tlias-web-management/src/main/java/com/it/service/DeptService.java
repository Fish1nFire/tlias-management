package com.it.service;

import com.it.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findall();

    void deleteByid(Integer id);

    void addDept(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
