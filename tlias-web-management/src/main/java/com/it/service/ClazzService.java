package com.it.service;

import com.it.pojo.Clazz;
import com.it.pojo.ClazzQueryParam;
import com.it.pojo.PageResult;

public interface ClazzService {
    /**
     * 条件分页查询班级列表
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级信息
     */
    void delete(Integer id);

    /**
     * 添加班级信息
     */
    void addClazz(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    Clazz getById(Integer id);
}
