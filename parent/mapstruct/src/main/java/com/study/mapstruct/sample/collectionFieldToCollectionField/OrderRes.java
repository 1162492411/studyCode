package com.study.mapstruct.sample.collectionFieldToCollectionField;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class OrderRes{
    private Long id;
    private Long orderNo;
    private List<String> saleItemNames;
    private Set<String> subNoSet;
}