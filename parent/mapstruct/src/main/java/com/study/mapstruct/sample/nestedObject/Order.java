package com.study.mapstruct.sample.nestedObject;

import lombok.Data;

import java.util.Date;

@Data
public class Order{
    private Long id;
    private Long no;
    private Long recyclerId;
    private Merchant me;
}