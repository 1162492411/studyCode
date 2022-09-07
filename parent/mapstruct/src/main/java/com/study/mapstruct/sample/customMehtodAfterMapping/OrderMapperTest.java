package com.study.mapstruct.sample.customMehtodAfterMapping;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class OrderMapperTest {
    @Test
    public void personDTOToPerson() {
        //准备入参
        OrderMapper orderMapper = OrderMapper.INSTANCE;
        Order order = new Order();
        order.setId(222L);
        order.setNo(1L);
        order.setSaleItemNameList(Arrays.asList("商品1","商品2","商品3"));
        order.setSubNoSet(new HashSet<>(Arrays.asList("sub001","sub002")));
        //转换
        OrderRes orderRes = orderMapper.dbToRes(order);
        //判断出参
        System.out.println(JSON.toJSONString(orderRes));
        Assert.assertEquals(order.getId(),orderRes.getId());
        Assert.assertEquals(order.getNo(),orderRes.getOrderNo());
    }
}
