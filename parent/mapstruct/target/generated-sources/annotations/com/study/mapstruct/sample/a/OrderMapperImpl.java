package com.study.mapstruct.sample.a;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-04T21:52:44+0800",
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

        return orderRes;
    }
}
