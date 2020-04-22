package com.hotel.dao;

import com.hotel.pojo.Hotels;

import java.util.List;

public interface HotelsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hotels record);

    int insertSelective(Hotels record);

    Hotels selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hotels record);

    int updateByPrimaryKey(Hotels record);

    List<Hotels> selectHotelRoomsByHotelId(Integer hotelId);

}