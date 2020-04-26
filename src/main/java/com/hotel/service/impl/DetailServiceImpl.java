package com.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.internal.$Gson$Preconditions;
import com.hotel.common.ServerResponse;
import com.hotel.dao.DetilsMapper;
import com.hotel.pojo.Detils;
import com.hotel.service.IDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-26 11:35
 **/
@Service("iDetailService")
public class DetailServiceImpl implements IDetailService {

    @Autowired
    private DetilsMapper detilsMapper;

    public ServerResponse add(Integer userId, Detils detail){
        detail.setUserId(userId);
        int rowCount=detilsMapper.insert(detail);
        if (rowCount>0){
            Map result= Maps.newHashMap();
            result.put("detailsId",detail.getId());
            return ServerResponse.createBySuccess("新建入住详细成功",result);
        }
        return ServerResponse.createByErrorMessage("新建入住详细失败");
    }

    public ServerResponse<String> del(Integer userId,Integer detailId){
        int rowCount=detilsMapper.deleteByDetailIdUserId(userId,detailId);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("删除入住详细成功");
        }
        return ServerResponse.createByErrorMessage("删除入住详细失败");
    }

    public ServerResponse update(Integer userId, Detils detail){
        detail.setUserId(userId);
        int rowCount=detilsMapper.updateByDetail(detail);
        if (rowCount>0){
            return ServerResponse.createBySuccess("更新入住详细成功");
        }
        return ServerResponse.createByErrorMessage("更新入住详细失败");
    }

    public ServerResponse<Detils> sel(Integer userId,Integer detailId){
        Detils detail=detilsMapper.selectByIdUserId(detailId,userId);
        if (detail==null){
            return ServerResponse.createByErrorMessage("无法查询该入住详细");
        }
        return ServerResponse.createBySuccess("查询入住详细成功",detail);

    }

    public ServerResponse<PageInfo> list(Integer userId,int pageSum,int pageSize){
        PageHelper.startPage(pageSum,pageSize);
        List<Detils> detilsList=detilsMapper.selectByUsetrId(userId);
        PageInfo pageInfo=new PageInfo(detilsList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}

