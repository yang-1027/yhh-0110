package com.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;


import com.hotel.dao.HotelsMapper;
import com.hotel.pojo.Hotels;
import com.hotel.service.IHotelService;
import com.hotel.util.DateTimeUtil;
import com.hotel.util.PropertiesUtil;
import com.hotel.vo.HotelDetailVo;
import com.hotel.vo.HotelListVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    public ServerResponse addOrUpdateHotel(Hotels hotels){
        if (hotels!=null){
            if (StringUtils.isNotBlank(hotels.getSubImages())){
                String[] subImageArray=hotels.getSubImages().split(",");
                if (subImageArray.length>0){
                    hotels.setMainImage(subImageArray[0]);
                }
            }

            if (hotels.getId()!=null){
                int rowCount=hotelsMapper.updateByPrimaryKey(hotels);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("更新酒店成功");
                }
                return ServerResponse.createBySuccessMessage("更新酒店失败");
            }else {
                int rowCount=hotelsMapper.insert(hotels);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("新增酒店成功");
                }
            }
        }
        return ServerResponse.createBySuccessMessage("新增酒店失败");


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

    public ServerResponse<HotelDetailVo> getHotelDetail(Integer hotelId){
        if (hotelId==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Hotels hotels=hotelsMapper.selectByPrimaryKey(hotelId);
        if (hotels==null){
            return ServerResponse.createBySuccessMessage("未找到该酒店,该酒店可能已删除或不存在");
        }
        HotelDetailVo hotelDetailVo=assembleHotelDetail(hotels);
        return ServerResponse.createBySuccess(hotelDetailVo);
    }

    private HotelDetailVo assembleHotelDetail(Hotels hotels){
        HotelDetailVo hotelDetailVo=new HotelDetailVo();
        hotelDetailVo.setId(hotels.getId());
        hotelDetailVo.setName(hotels.getName());
        hotelDetailVo.setUserId(hotels.getUserId());
        hotelDetailVo.setDetail(hotels.getDetail());
        hotelDetailVo.setMainImage(hotels.getMainImage());
        hotelDetailVo.setSubImages(hotels.getSubImages());
        hotelDetailVo.setSortOrder(hotels.getSortOrder());
        hotelDetailVo.setStar(hotels.getStar());
        hotelDetailVo.setStatus(hotels.getStatus());

        hotelDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","ftp://173.82.242.180/"));

        hotelDetailVo.setCreateTime(DateTimeUtil.dateToStr(hotels.getCreateTime()));
        hotelDetailVo.setUpdateTime(DateTimeUtil.dateToStr(hotels.getUpdateTime()));
        return hotelDetailVo;
    }

    public ServerResponse<PageInfo> getHotelList(int  pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Hotels> hotelsList=hotelsMapper.selectHotelList();
        List<HotelListVo> hotelListVoList= Lists.newArrayList();
        for (Hotels hotels:hotelsList){
            HotelListVo hotelListVo=assembleHotelList(hotels);
            hotelListVoList.add(hotelListVo);
        }
        PageInfo pageResult=new PageInfo(hotelsList);
        pageResult.setList(hotelListVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    private HotelListVo assembleHotelList(Hotels hotels){
        HotelListVo hotelListVo=new HotelListVo();
        hotelListVo.setId(hotels.getId());
        hotelListVo.setUserId(hotels.getUserId());
        hotelListVo.setName(hotels.getName());
        hotelListVo.setMainImage(hotels.getMainImage());
        hotelListVo.setDetail(hotels.getDetail());
        hotelListVo.setStar(hotels.getStar());
        hotelListVo.setStatus(hotels.getStatus());

        hotelListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","ftp://173.82.242.180/"));
        return hotelListVo;
    }

    public ServerResponse<PageInfo> searchHotel(String hotelName,Integer hotelId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(hotelName)){
            hotelName =new StringBuilder().append("%").append(hotelName).append("%").toString();
        }
        List<Hotels> hotelsList=hotelsMapper.selectHotelsByIdAndName(hotelName,hotelId);
        List<HotelListVo> hotelListVoList= Lists.newArrayList();
        for (Hotels hotels:hotelsList){
            HotelListVo hotelListVo=assembleHotelList(hotels);
            hotelListVoList.add(hotelListVo);
        }
        PageInfo pageResult=new PageInfo(hotelsList);
        pageResult.setList(hotelListVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

}

