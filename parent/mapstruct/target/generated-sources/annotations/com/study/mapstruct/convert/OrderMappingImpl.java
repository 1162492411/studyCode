package com.study.mapstruct.convert;

import com.study.mapstruct.persistant.Order;
import com.study.mapstruct.request.OrderAddReq;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-04T21:52:44+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
public class OrderMappingImpl implements OrderMapping {

    @Override
    public Order reqToDb(OrderAddReq req) {
        if ( req == null ) {
            return null;
        }

        Order order = new Order();

        return order;
    }
}
