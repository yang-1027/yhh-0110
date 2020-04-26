package com.hotel.dao;

import com.hotel.pojo.Detils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetilsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Detils record);

    int insertSelective(Detils record);

    Detils selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detils record);

    int updateByPrimaryKey(Detils record);

    int deleteByDetailIdUserId(@Param("userId")Integer userId,@Param("detailId")Integer detailId);

    int updateByDetail(Detils record);

    Detils selectByIdUserId(@Param("detailId")Integer detailId,@Param("userId")Integer userId);

    List<Detils> selectByUsetrId(@Param("userId")Integer userId);


}