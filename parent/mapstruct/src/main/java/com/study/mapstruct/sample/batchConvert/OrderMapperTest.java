package com.study.mapstruct.sample.batchConvert;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderMapperTest {
    @Test
    public void personDTOToPerson() {
        //准备入参
        OrderMapper orderMapper = OrderMapper.INSTANCE;
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setId(222L);
        order.setNo(20220101222L);
        orderList.add(order);
        //添加第二组数据
        Order order2 = new Order();
        order2.setId(223L);
        order2.setNo(20220101223L);
        orderList.add(order2);
        //转换
        List<OrderRes> resList = orderMapper.dbListToResList(orderList);
        //判断出参
        Assert.assertNotNull(resList);
        Assert.assertEquals(2,resList.size());
        for (int i = 0; i < resList.size(); i++) {
            Assert.assertEquals(orderList.get(i).getId(),resList.get(i).getId());
            Assert.assertEquals(orderList.get(i).getNo(),resList.get(i).getOrderNo());
        }
    }
}
