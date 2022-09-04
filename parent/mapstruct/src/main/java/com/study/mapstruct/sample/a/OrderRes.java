package com.study.mapstruct.sample.a;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRes {
    private Long id;
    private Long orderNo;
    private Date orderCreateDate;
}
