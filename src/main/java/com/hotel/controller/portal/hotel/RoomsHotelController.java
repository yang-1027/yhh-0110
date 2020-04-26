package com.hotel.controller.portal.hotel;

import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.dao.HotelsMapper;
import com.hotel.pojo.Room;
import com.hotel.pojo.User;
import com.hotel.service.IHotelService;
import com.hotel.service.IRoomService;
import com.hotel.service.IUserService;
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
 * @create: 2020-04-25 12:08
 **/
@Controller
@RequestMapping("/hotel/rooms")
public class RoomsHotelController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private IRoomService iRoomService;
    @Autowired
    private HotelsMapper hotelsMapper;

    @RequestMapping("roomAdd_or_update.do")
    @ResponseBody
    public ServerResponse hotelRoomAddOrUpdate(HttpSession session,Room room, Integer hotelId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            int rowCount=hotelsMapper.hotelIsUser(user.getId(),hotelId);
            if (rowCount>0){
                return iRoomService.hotelSaveOrUpdateRoom(room,hotelId);
            }
            return ServerResponse.createByErrorMessage("添加或更新房间失败");

        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("set_room_status.do")
    @ResponseBody
    public ServerResponse setRoomStatus(HttpSession session, Integer roomId,Integer status,Integer hotelId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店权限
            return iRoomService.hotelSetRoomStatus(roomId,status,hotelId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize, Integer typeId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //管理员
            return iRoomService.getRoomList(pageNum,pageSize,typeId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

}

