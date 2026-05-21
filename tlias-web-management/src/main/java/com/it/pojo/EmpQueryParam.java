package com.it.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
@Data
public class EmpQueryParam {
    Integer page;
    Integer pageSize;
    String name;
    Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate end;
}
