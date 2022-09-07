package com.study.mapstruct.sample.collectionFieldToCollectionField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-07T22:33:30+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_262 (AdoptOpenJDK)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderRes dbToRes(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderRes orderRes = new OrderRes();

        orderRes.setOrderNo( order.getNo() );
        List<String> list = order.getSaleItemNameList();
        if ( list != null ) {
            orderRes.setSaleItemNames( new ArrayList<String>( list ) );
        }
        orderRes.setId( order.getId() );
        Set<String> set = order.getSubNoSet();
        if ( set != null ) {
            orderRes.setSubNoSet( new HashSet<String>( set ) );
        }

        return orderRes;
    }
}
