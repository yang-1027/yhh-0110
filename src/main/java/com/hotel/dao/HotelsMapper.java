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

    List<Hotels> selectHotelListByUserId(Integer userId);

    List<Hotels> selectHotelsByIdAndName(@Param("hotelsName")String hotelsName,@Param("hotelsId")Integer hotelId);

    List<Hotels> userSelectHotelByNameAndHotelId(@Param("hotelsName")String hotelsName,@Param("hotelsId")Integer hotelId);

    int setStatusByIdAndUserId(@Param("id")Integer id,@Param("status")Integer status,@Param("userId")Integer userId);

    int hotelIsUser(@Param("userId")Integer userId,@Param("hotelId")Integer hotelId);

}