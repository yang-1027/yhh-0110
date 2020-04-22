package com.hotel.service;

import com.hotel.common.ServerResponse;
import com.hotel.pojo.Hotels;

import java.util.List;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-22 09:56
 **/
public interface IHotelService {

    ServerResponse addHotel(String hotelName, Integer userId);

    ServerResponse updateHotelName(Integer hotelId,String hotelName);

    ServerResponse<List<Hotels>> getRoomsBykHotel(Integer hotelId);

}

