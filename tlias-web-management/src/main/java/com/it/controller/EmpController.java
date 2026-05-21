package com.it.controller;

import com.it.pojo.Emp;
import com.it.pojo.EmpQueryParam;
import com.it.pojo.PageResult;
import com.it.pojo.Result;
import com.it.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService EmpService;

    /**
     * 分页查询,条件查询
     *
     * @return
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10")Integer pageSize,
//                        String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//
//        log.info("分页请求参数{}，{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageresult= EmpService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageresult);
//    }

    @GetMapping// //前端传递多个参数，可以将其封装在一个对象中
    public Result page(EmpQueryParam empQueryParam){

        log.info("分页请求参数{}",empQueryParam);
        PageResult<Emp> pageresult= EmpService.page(empQueryParam);
        return Result.success(pageresult);
    }

    /**
     * 新增员工信息和员工工作经历信息
     */
    @PostMapping
    public Result sava(@RequestBody Emp emp){
        EmpService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工信息和员工工作经历信息
     */
    @DeleteMapping
    public Result delete(@RequestParam List< Integer> ids){
        log.info("批量删除员工信息{}",ids);
        EmpService.delete(ids);
        return Result.success();
    }

    /**更新员工信息
     * 查询回显
     * 更新数据
     * */
    @GetMapping ("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询回显的员工id是"+id);
        Emp emp  = EmpService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息{}",emp);
        EmpService.update(emp);
        return Result.success();
    }

}
