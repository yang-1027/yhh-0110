package com.hotel.controller.portal.user;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.service.IHotelService;
import com.hotel.vo.HotelDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-24 10:43
 **/
@Controller
@RequestMapping("/getHotels/")
public class UserHotelsController {
    @Autowired
    private IHotelService iHotelService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<HotelDetailVo>detail(Integer hotelId){
        return iHotelService.getHotelDetail(hotelId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo>list(@RequestParam(value = "keyword",required= false)String keyword,
                                        @RequestParam(value = "hotelId",required= false)Integer hotelId,
                                        @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                        @RequestParam(value = "orderBy",defaultValue = "")String orderBy){

        return iHotelService.getHotelByKeyword(keyword,hotelId,pageNum,pageSize,orderBy);
    }
}

