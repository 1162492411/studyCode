package com.study.mapstruct.sample.nestedObject;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class OrderMapperTest {
    @Test
    public void personDTOToPerson() {
        //准备入参
        OrderMapper orderMapper = OrderMapper.INSTANCE;
        Order order = new Order();
        order.setId(222L);
        order.setNo(1L);
        order.setRecyclerId(333L);
        Merchant merchant = new Merchant();
        merchant.setId(333L);
        merchant.setName("张三");
        merchant.setSex(new Byte("1"));
        order.setMe(merchant);
        //转换
        OrderRes orderRes = orderMapper.dbToRes(order);
        //判断出参
        System.out.println(JSON.toJSONString(orderRes));
        Assert.assertEquals(order.getId(),orderRes.getId());
        Assert.assertEquals(order.getNo(),orderRes.getOrderNo());
        Assert.assertNull(orderRes.getOrderCreateDate());
        Assert.assertEquals(merchant.getId(),orderRes.getRecyclerId());
        Assert.assertEquals(merchant.getName(),orderRes.getRecyclerName());
    }
}
