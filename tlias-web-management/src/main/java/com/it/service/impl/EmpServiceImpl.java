package com.it.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.it.mapper.EmpExprMapper;
import com.it.mapper.EmpMapper;
import com.it.pojo.*;
import com.it.service.EmpService;
import com.it.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     * 分页查询实现pagehelper
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> emplist = empMapper.list(empQueryParam);
        //3.封装结果并返回
        Page<Emp> p = (Page<Emp>) emplist;
        return new PageResult<Emp> (p.getTotal(),p.getResult());

    }

    /**
     * 新增员工信息和员工工作经历信息
     * @param emp
     */
    @Transactional(rollbackFor = Exception.class)// 开启事务，确保两个操作同时成功或同时失败
                                                    //默认情况下只有RuntimeException才会回滚，这里设置只要有异常就会回滚
    @Override
    public void save(Emp emp) {
        //保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，设置员工ID为新增员工的ID
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 删除员工和员工工作经历信息
     */
    @Override
    public void delete(List<Integer> ids) {
        try {
            //删除员工基本信息
            empMapper.deleteByIds(ids);
            //删除员工工作经历信息
            empExprMapper.deleteByEmpIds(ids);
            log.info("成功删除员工信息，员工ID列表：{}", ids);
        } catch (Exception e) {
            log.error("删除员工信息失败，员工ID列表：{}", ids, e);
            throw e; // 重新抛出异常
        } finally {
            // 记录操作日志
            log.info("【删除操作日志】员工ID列表：{}，删除操作已执行", ids);
        }
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        //1.根据员工ID更新员工基本信息
           //更新时间
        emp.setUpdateTime(LocalDateTime.now());
           //更新基本信息
        empMapper.updateById(emp);
        //2.根据ID更新员工工作经历信息
          //删除旧的工作经历
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));
          //插入新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，设置员工ID为新增员工的ID
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            //1. 生成JWT令牌
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }


}
