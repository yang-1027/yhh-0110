package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Room;
import com.hotel.vo.RoomDetailVo;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-22 12:47
 **/
public interface IRoomService {

    ServerResponse saveOrUpdateRoom(Room room);

    ServerResponse<String> setRoomStatus(Integer roomId,Integer status);

    ServerResponse<RoomDetailVo> manageRoomDetail(Integer roomId);

    ServerResponse<PageInfo> getRoomList(int pageNum, int pageSize);

}

