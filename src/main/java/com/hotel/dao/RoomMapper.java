package com.hotel.dao;

import com.hotel.pojo.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectRoomList(Integer typeId);

    int hotelSetRoomStatus(@Param("roomId") Integer roomId, @Param("status") Integer status, @Param("hotelId") Integer hotelId);

    List<Room> selectRoomListById(Integer roomId);

}