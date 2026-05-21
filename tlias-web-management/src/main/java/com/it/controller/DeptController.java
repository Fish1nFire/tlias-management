package com.it.controller;

import com.it.pojo.Dept;
import com.it.pojo.Result;
import com.it.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Slf4j// 日志
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    /**
     * 查询所有部门信息
     */
    //@RequestMapping(value = "/depts",method = GET)
    @GetMapping("/depts")
    public Result list() {
        //System.out.println("查询所有部门的信息");
        log.info("查询所有部门信息");
        List<Dept> deptList= deptService.findall();
        return Result.success(deptList);
    }
    /**
     * 删除部门信息
     */
    /*方式一：HttpServletRequest request获取参数
    * */

    /*方式二：@RequestParam获取参数
    * 一旦声明必须传递参数，否则会报错，因为required=true是默认的
     */

    /*方式三：省略@RequestParam，要求前端传递的请求参数名和后端方法内形参名一致
     */
    @DeleteMapping("/depts")
    public Result delete (Integer id){
        //System.out.println("删除的部门id是"+ id);
        log.info("删除的部门id是{}",id);
        deptService.deleteByid(id);
        return Result.success();
    }

    /**
     * 添加部门信息
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        //System.out.println("添加的部门信息是"+dept);
        log.info("添加的部门信息是{}",dept);
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        //System.out.println("查询回显的部门id是"+id);
        log.info("查询回显的部门id是{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    /**
     * 修改部门信息
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改的部门信息是"+dept);
        log.info("修改的部门信息是{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
