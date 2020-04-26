package com.hotel.service.impl;

import com.google.common.collect.Maps;
import com.hotel.common.ServerResponse;
import com.hotel.dao.OrderMapper;
import com.hotel.pojo.Order;
import com.hotel.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-27 00:10
 **/
@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    public ServerResponse pay(Long orderNo,Integer userId,String path){
        Map<String,String> resultMap= Maps.newHashMap();
        Order order=orderMapper.selectByUserIdAndOrderNo(userId,orderNo);
        if (order==null){
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }
        resultMap.put("orderNo",String.valueOf(order.getOrderNo()));

    }
}

