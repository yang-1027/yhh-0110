package com.hotel.service.impl;

import com.hotel.common.ServerResponse;


import com.hotel.dao.HotelsMapper;
import com.hotel.pojo.Hotels;
import com.hotel.service.IHotelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-22 09:57
 **/
@Service("iHotelService")
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private HotelsMapper hotelsMapper;

    Logger logger= LoggerFactory.getLogger(HotelServiceImpl.class);

    public ServerResponse addHotel(String hotelName,Integer userId){
        if (userId==null||StringUtils.isBlank(hotelName)){
            return ServerResponse.createByErrorMessage("酒店添加错误");
        }
        Hotels hotels=new Hotels();
        hotels.setName(hotelName);
        hotels.setUserId(userId);
        hotels.setStatus(true);

        int rowCount=hotelsMapper.insert(hotels);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("酒店添加成功");
        }
        return ServerResponse.createByErrorMessage("酒店添加失败");
    }

    public ServerResponse updateHotelName(Integer hotelId,String hotelName){
        if (hotelId==null||StringUtils.isBlank(hotelName)){
            return ServerResponse.createByErrorMessage("酒店添加错误");
        }
        Hotels hotels=new Hotels();
        hotels.setId(hotelId);
        hotels.setName(hotelName);
        int rowCount=hotelsMapper.updateByPrimaryKeySelective(hotels);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("更新酒店名称成功");
        }
        return ServerResponse.createByErrorMessage("更新酒店名称失败");
    }

    public ServerResponse<List<Hotels>> getRoomsBykHotel(Integer hotelId){
        List<Hotels> hotelsList=hotelsMapper.selectHotelRoomsByHotelId(hotelId);
        if (CollectionUtils.isEmpty(hotelsList)){
            logger.info("为找到酒店房间分类");
        }
        return ServerResponse.createBySuccess(hotelsList);
    }

}

