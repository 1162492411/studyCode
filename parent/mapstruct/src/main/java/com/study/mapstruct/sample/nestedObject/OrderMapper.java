package com.study.mapstruct.sample.nestedObject;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "no", target = "orderNo"),
            @Mapping(source = "me.id", target = "recyclerId"),
            @Mapping(source = "me.name", target = "recyclerName"),
            @Mapping(source = "me", target = "merchantInfo")
    })
    OrderRes dbToRes(Order order);
}