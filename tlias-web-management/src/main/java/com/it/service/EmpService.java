package com.it.service;

import com.it.pojo.Emp;
import com.it.pojo.EmpQueryParam;
import com.it.pojo.LoginInfo;
import com.it.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @return
     */
    public PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息和员工工作经历信息
     */
    void save(Emp emp);

    /**
     * 删除员工和员工工作经历信息
     */
    void delete(List<Integer> ids);

    /**
     * 根据员工ID查询员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     */
    void update(Emp emp);

    /**
     * 登录
     */
    LoginInfo login(Emp emp);
}
