package com.study.mapstruct.sample.customMehtodAfterMapping;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrderRes{
    private Long id;
    private Long orderNo;
    private List<String> saleItemNames;
    private Integer saleItemCount;
    private Set<String> subNoSet;
    private Boolean containsSubNo;
}