package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Hotels;
import com.hotel.vo.HotelDetailVo;

import java.util.List;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-22 09:56
 **/
public interface IHotelService {

    ServerResponse addOrUpdateHotel(Hotels hotels);

    ServerResponse updateHotelName(Integer hotelId,String hotelName);

    ServerResponse<List<Hotels>> getRoomsBykHotel(Integer hotelId);

    ServerResponse<HotelDetailVo> getHotelDetail(Integer hotelId);

    ServerResponse<PageInfo> getHotelList(int  pageNum, int pageSize);

    ServerResponse<PageInfo> searchHotel(String hotelName,Integer hotelId,int pageNum,int pageSize);


}

