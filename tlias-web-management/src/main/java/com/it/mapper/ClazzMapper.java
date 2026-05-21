package com.it.mapper;

import com.it.pojo.Clazz;
import com.it.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 条件分页查询班级列表
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级信息
     */
    void delete(Integer id);

    /**
     * 添加班级信息
     */
    void insert(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    Clazz getById(Integer id);
}
