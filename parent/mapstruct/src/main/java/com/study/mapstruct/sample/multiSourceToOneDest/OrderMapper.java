package com.study.mapstruct.sample.multiSourceToOneDest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "order.no", target = "orderNo"),
            //这里注意:出参对象中有id字段,入参对象有两个id字段,因此需要显式指定映射关系
            //如果出参对象中不需要id,可以先任意指定一个id映射然后添加ignore=true
            @Mapping(source = "order.id", target = "id"),
            @Mapping(source = "mer.id", target = "recyclerId"),
            @Mapping(source = "mer.name", target = "recyclerName")
    })
    OrderRes dbToRes(Order order, Merchant mer);
}