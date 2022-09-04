package com.study.mapstruct.sample.b;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-04T22:17:30+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderRes dbToRes(Order order, Merchant mer) {
        if ( order == null && mer == null ) {
            return null;
        }

        OrderRes orderRes = new OrderRes();

        if ( order != null ) {
            orderRes.setOrderNo( order.getNo() );
            orderRes.setId( order.getId() );
        }
        if ( mer != null ) {
            orderRes.setRecyclerId( mer.getId() );
            orderRes.setRecyclerName( mer.getName() );
        }

        return orderRes;
    }
}
