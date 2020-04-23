package com.hotel.dao;

import com.hotel.pojo.Hotels;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hotels record);

    int insertSelective(Hotels record);

    Hotels selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hotels record);

    int updateByPrimaryKey(Hotels record);

    List<Hotels> selectHotelRoomsByHotelId(Integer hotelId);

    List<Hotels> selectHotelList();

    List<Hotels> selectHotelsByIdAndName(@Param("hotelsName")String hotelsName,@Param("hotelsId")Integer hotelId);

}