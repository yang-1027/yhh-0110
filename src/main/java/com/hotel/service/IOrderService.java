package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.vo.OrderVo;

import java.util.Map;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-27 00:10
 **/
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String,String> params);

    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);

    ServerResponse createOrder(Integer userId, Integer roomId, Integer detailId, String checkInDate,String leaveDate, Integer quantity);

    ServerResponse<String> cancel(Integer userId,Long orderNo);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    //管理员
    ServerResponse<PageInfo> manageList(int pageNum,int pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize);

    //酒店
    ServerResponse<PageInfo> hotelList(Integer hotelId,int pageNum,int pageSize);

    ServerResponse<OrderVo> hotelDetail(Integer hotelId,Long orderNo);

    ServerResponse<PageInfo> hotelSearch(Integer hotelId,Long orderNo,int pageNum,int pageSize);

    ServerResponse<String> hotelAgree(Integer userId, Long orderNo);

}

