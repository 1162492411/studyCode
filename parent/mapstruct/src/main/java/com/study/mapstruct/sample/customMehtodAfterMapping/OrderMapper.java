package com.study.mapstruct.sample.customMehtodAfterMapping;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "no", target = "orderNo"),
            @Mapping(source = "saleItemNameList", target = "saleItemNames")
    })
    OrderRes dbToRes(Order order);

    @AfterMapping
    default void quickReqToSearchModelAfter(Order ord, @MappingTarget OrderRes des) {
        //设置商品数量
        if(CollectionUtils.isEmpty(ord.getSaleItemNameList())){
            des.setSaleItemCount(0);
        }else{
            des.setSaleItemCount(ord.getSaleItemNameList().size());
        }
        //设置是否包含子订单
        //既然已经进行过字段映射了，其实也可以使用出参对象中的字段了
        des.setContainsSubNo(!CollectionUtils.isEmpty(des.getSubNoSet()));
    }

}
