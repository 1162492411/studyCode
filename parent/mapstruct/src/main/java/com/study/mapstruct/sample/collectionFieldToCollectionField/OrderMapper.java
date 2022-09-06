package com.study.mapstruct.sample.collectionFieldToCollectionField;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "no", target = "orderNo"),
            @Mapping(source = "saleItemNameList", target = "saleItemNames")
    })
    OrderRes dbToRes(Order order);

}
