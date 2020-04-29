package com.hotel.controller.portal.hotel;

import com.github.pagehelper.PageInfo;
import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Hotels;
import com.hotel.pojo.User;
import com.hotel.service.IHotelService;
import com.hotel.service.IOrderService;
import com.hotel.service.IUserService;
import com.hotel.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-29 00:35
 **/
@Controller
@RequestMapping("/hotel/order")
public class OrderHotelController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IHotelService iHotelService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session, Integer hotelId,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店用户
            if (iHotelService.checkHotelUser(user.getId(),hotelId).isSuccess()){
                return iOrderService.hotelList(hotelId,pageNum,pageSize);
            }
            return ServerResponse.createByErrorMessage("此酒店用户不是此账号");
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpSession session,Integer hotelId, Long orderNo){

        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店用户
            return iOrderService.hotelDetail(hotelId,orderNo);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session,Integer hotelId, Long orderNo,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iOrderService.hotelSearch(hotelId,orderNo,pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("agree.do")
    @ResponseBody
    public ServerResponse<String> orderAgree(HttpSession session,Long orderNo){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店用户
            return iOrderService.hotelAgree(user.getId(),orderNo);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需酒店权限");
        }
    }
}

