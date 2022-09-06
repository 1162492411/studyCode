package com.study.mapstruct.sample.methodReturnToField;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRes {
    private Long id;
    private Long orderNo;
    private String orderDay;
    private Integer status;
    private Date createDate;
}
