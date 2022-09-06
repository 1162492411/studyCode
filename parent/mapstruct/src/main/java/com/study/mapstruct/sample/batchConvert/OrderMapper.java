package com.study.mapstruct.sample.batchConvert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "no", target = "orderNo")
    })
    OrderRes dbToRes(Order order);

    List<OrderRes> dbListToResList(List<Order> orders);

}
