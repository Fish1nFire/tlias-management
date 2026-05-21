package com.it.mapper;

import com.it.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EmpExprMapper {

    /**
     * 批量新增员工工作经历信息
     * @param exprList
     */

    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工ID删除员工工作经历信息
     * @param ids
     */
    void deleteByEmpIds(List<Integer> ids);
}
