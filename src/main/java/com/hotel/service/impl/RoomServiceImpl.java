package com.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.dao.HotelsMapper;
import com.hotel.dao.RoomMapper;
import com.hotel.pojo.Hotels;
import com.hotel.pojo.Room;
import com.hotel.service.IRoomService;
import com.hotel.util.DateTimeUtil;
import com.hotel.util.PropertiesUtil;
import com.hotel.vo.RoomDetailVo;
import com.hotel.vo.RoomListVo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-22 12:47
 **/
@Service("iRoomService")
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private HotelsMapper hotelsMapper;

//    public ServerResponse<List<Hotels>> getRoomsParallelHotel(Integer hotelId){
//
//    }
    public ServerResponse saveOrUpdateRoom(Room room){
        if (room!=null){
            if (StringUtils.isNotBlank(room.getSubImages())){
                String[] subImageArray=room.getSubImages().split(",");
                if (subImageArray.length>0){
                    room.setMainImage(subImageArray[0]);
                }
            }
            if (room.getId()!=null){
                int rowCount=roomMapper.updateByPrimaryKey(room);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("更新房间成功");
                }
                return ServerResponse.createBySuccessMessage("更新房间失败");
            }else {
                int rowCount=roomMapper.insert(room);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("新增房间成功");
                }
                return ServerResponse.createBySuccessMessage("新增房间失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增房间或更新房间错误");
    }

    public ServerResponse hotelSaveOrUpdateRoom(Room room,Integer hotelId){
        if (room!=null&&hotelId!=null){
            room.setTypeId(hotelId);
            if (StringUtils.isNotBlank(room.getSubImages())){
                String[] subImageArray=room.getSubImages().split(",");
                if (subImageArray.length>0){
                    room.setMainImage(subImageArray[0]);
                }
            }
            if (room.getId()!=null){
                int rowCount=roomMapper.updateByPrimaryKey(room);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("更新房间成功");
                }
                return ServerResponse.createBySuccessMessage("更新房间失败");
            }else {
                int rowCount=roomMapper.insert(room);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("新增房间成功");
                }
                return ServerResponse.createBySuccessMessage("新增房间失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增房间或更新房间错误");
    }



    public ServerResponse<String> setRoomStatus(Integer roomId,Integer status){
        if (roomId==null||status==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Room room=new Room();
        room.setId(roomId);
        room.setStatus(status);
        int rowCount=roomMapper.updateByPrimaryKeySelective(room);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("更改房间状态成功");
        }
        return ServerResponse.createByErrorMessage("更改房间状态失败");
    }

    public ServerResponse<String> hotelSetRoomStatus(Integer roomId,Integer status,Integer hotelId){
        if (roomId==null||status==null||hotelId==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int rowCount=roomMapper.hotelSetRoomStatus(roomId,status,hotelId);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("更改房间状态成功");
        }
        return ServerResponse.createByErrorMessage("更改房间状态失败");
    }

    public ServerResponse<RoomDetailVo> manageRoomDetail(Integer roomId){
        if (roomId==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Room room=roomMapper.selectByPrimaryKey(roomId);
        if (room==null){
            return ServerResponse.createByErrorMessage("房间不存在或已删除");
        }
        //VO对象--value object
        //pojo->bo(business object)->vo(view object)
        RoomDetailVo roomDetailVo=assembleRoomDetailVo(room);
        return ServerResponse.createBySuccess(roomDetailVo);
    }

    private RoomDetailVo assembleRoomDetailVo(Room room){
        RoomDetailVo roomDetailVo=new RoomDetailVo();
        roomDetailVo.setId(room.getId());
        roomDetailVo.setMainImage(room.getMainImage());
        roomDetailVo.setSubImage(room.getSubImages());
        roomDetailVo.setPrice(room.getPrice());
        roomDetailVo.setHotelId(room.getTypeId());
        roomDetailVo.setDetails(room.getDetail());
        roomDetailVo.setName(room.getName());
        roomDetailVo.setStock(room.getStock());
        roomDetailVo.setStatus(room.getStatus());

        //imageHost
        roomDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","ftp://173.82.242.180/"));
        Hotels hotels=hotelsMapper.selectByPrimaryKey(room.getTypeId());
        if (hotels==null){
            roomDetailVo.setHotelId(0);
        }else {
            roomDetailVo.setHotelId(hotels.getId());
        }
        //createTime
        //updateTime
        roomDetailVo.setCreateTime(DateTimeUtil.dateToStr(room.getCreateTime()));
        roomDetailVo.setUpdateTime(DateTimeUtil.dateToStr(room.getUpdateTime()));
        return roomDetailVo;
    }

    public ServerResponse<PageInfo> getRoomList(int pageNum,int pageSize,Integer typeId){
        //startPage--start
        //填充sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Room> roomList=roomMapper.selectRoomList(typeId);
        List<RoomListVo> roomListVoList= Lists.newArrayList();
        for (Room roomItem:roomList){
            RoomListVo roomListVo=assembleRoomListVo(roomItem);
            roomListVoList.add(roomListVo);
        }
        PageInfo pageResult=new PageInfo(roomList);
        pageResult.setList(roomListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private RoomListVo assembleRoomListVo(Room room){
        RoomListVo roomListVo=new RoomListVo();
        roomListVo.setId(room.getId());
        roomListVo.setTypeId(room.getTypeId());
        roomListVo.setMainImage(room.getMainImage());
        roomListVo.setPrice(room.getPrice());
        roomListVo.setName(room.getName());
        roomListVo.setStatus(room.getStatus());
        roomListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","ftp://173.82.242.180/"));
        return roomListVo;
    }

}

