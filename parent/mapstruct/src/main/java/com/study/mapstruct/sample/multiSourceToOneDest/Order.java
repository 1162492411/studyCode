package com.study.mapstruct.sample.multiSourceToOneDest;

import lombok.Data;

import java.util.Date;

@Data
public class Order{
    private Long id;
    private Long no;
    private Date createDate;
    private Long recyclerId;
}