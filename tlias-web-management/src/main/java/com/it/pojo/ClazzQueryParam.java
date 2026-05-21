package com.it.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    Integer page;
    Integer pageSize;
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate end;
}
