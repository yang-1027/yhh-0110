package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Detils;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-26 11:34
 **/
public interface IDetailService {

    ServerResponse add(Integer userId, Detils detail);

    ServerResponse<String> del(Integer userId,Integer detailId);

    ServerResponse update(Integer userId, Detils detail);

    ServerResponse<Detils> sel(Integer userId,Integer detailId);

    ServerResponse<PageInfo> list(Integer userId, int pageSum, int pageSize);

}

