package com.hotel.dao;

import com.hotel.pojo.Detils;

public interface DetilsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Detils record);

    int insertSelective(Detils record);

    Detils selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detils record);

    int updateByPrimaryKey(Detils record);
}