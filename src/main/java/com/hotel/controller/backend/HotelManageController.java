package com.hotel.controller.backend;

import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Hotels;
import com.hotel.pojo.User;
import com.hotel.service.IHotelService;
import com.hotel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-22 09:21
 **/
@Controller
@RequestMapping(value = "/manage/hotels")
public class HotelManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IHotelService iHotelService;
    @RequestMapping("add_or_update_hotel.do")
    @ResponseBody
    public ServerResponse addOrUpdateHotel(HttpSession session, Hotels hotels){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iHotelService.addOrUpdateHotel(hotels);

        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("set_hotel_name.do")
    @ResponseBody
    public ServerResponse setHotelName(HttpSession session,Integer hotelId,String hotelname){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iHotelService.updateHotelName(hotelId,hotelname);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("get_Rooms.do")
    @ResponseBody
    public ServerResponse getRoomsParallelHotel(HttpSession session, Integer hotelId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iHotelService.getRoomsBykHotel(hotelId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getHotelDetails(HttpSession session,Integer hostId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iHotelService.getHotelDetail(hostId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员

            return iHotelService.getHotelList(pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session,String hotelName, Integer hotelId,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iHotelService.searchHotel(hotelName,hotelId,pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    public ServerResponse upload(MultipartFile file, HttpServletRequest request){
        String path=request.getSession().getServletContext().getRealPath("upload");
    }

}

