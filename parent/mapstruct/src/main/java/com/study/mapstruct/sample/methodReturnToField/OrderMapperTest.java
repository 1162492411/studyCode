package com.study.mapstruct.sample.methodReturnToField;

import org.junit.Assert;
import org.junit.Test;

public class OrderMapperTest {
    @Test
    public void personDTOToPerson() {
        //准备入参
        OrderMapper orderMapper = OrderMapper.INSTANCE;
        Order order = new Order();
        order.setId(222L);
        order.setNo(1L);

        //转换
        OrderRes orderRes = orderMapper.dbToRes(order);
        //判断出参
        Assert.assertEquals(order.getId(),orderRes.getId());
        Assert.assertEquals(order.getNo(),orderRes.getOrderNo());
    }
}
