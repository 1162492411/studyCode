package com.study.mapstruct.sample.collectionFieldToCollectionField;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Order{
    private Long id;
    private Long no;
    private List<String> saleItemNameList;
    private Set<String> subNoSet;
}