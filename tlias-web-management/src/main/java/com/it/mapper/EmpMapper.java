package com.it.mapper;


import com.it.pojo.Emp;
import com.it.pojo.EmpExpr;
import com.it.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/*
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    /**
     * 分页查询实现
     */
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc ")
    //Xml映射文件实现条件查询
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    /**
     * 批量删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据员工ID查询员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据员工ID更新员工基本信息
     * @param emp
     * @return
     */
    void updateById(Emp emp);

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
