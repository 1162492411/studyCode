package com.study.mapstruct.sample.multiSourceToOneDest;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRes {
    private Long id;
    private Long orderNo;
    private Date orderCreateDate;
    private Long recyclerId;
    private String recyclerName;
}
