package com.study.mapstruct.sample.c;

import com.study.mapstruct.sample.methodReturnToField.*;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-06T22:28:18+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderRes dbToRes(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderRes orderRes = new OrderRes();

        orderRes.setOrderNo( order.getNo() );
        orderRes.setId( order.getId() );

        orderRes.setOrderDay( BUtil.extractFromNo(order.getNo()) );
        orderRes.setStatus( EnumOrderStatus.PAYED.getStatus() );
        orderRes.setCreateDate( new java.util.Date() );

        return orderRes;
    }
}
