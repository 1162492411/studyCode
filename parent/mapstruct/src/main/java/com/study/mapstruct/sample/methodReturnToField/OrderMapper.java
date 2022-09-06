package com.study.mapstruct.sample.methodReturnToField;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
        @Mapping(source = "no", target = "orderNo"),
        @Mapping(target = "orderDay", expression = "java(com.study.mapstruct.sample.methodReturnToField.BUtil.extractFromNo(order.getNo()))"),
        @Mapping(target = "status", expression = "java(com.study.mapstruct.sample.methodReturnToField.EnumOrderStatus.PAYED.getStatus())"),
        @Mapping(target = "createDate", expression = "java(new java.util.Date())")
    })
    OrderRes dbToRes(Order order);


}