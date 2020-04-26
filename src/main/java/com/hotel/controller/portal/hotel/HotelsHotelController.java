package com.hotel.controller.portal.hotel;

import com.google.common.collect.Maps;
import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Hotels;
import com.hotel.pojo.Room;
import com.hotel.pojo.User;
import com.hotel.service.IFileService;
import com.hotel.service.IHotelService;
import com.hotel.service.IRoomService;
import com.hotel.service.IUserService;
import com.hotel.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-25 09:29
 **/

@Controller
@RequestMapping("/hotel/hotels")
public class HotelsHotelController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoomService iRoomService;
    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private IFileService iFileService;

    @RequestMapping("addOrUpdate.do")
    @ResponseBody
    public ServerResponse roomSave(HttpSession session, Hotels hotels){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店用户
            return iHotelService.hotelAddOrUpdateHotel(hotels,user.getId());
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店
            return iHotelService.hotelHotelList(user.getId(),pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("set_hotel_status.do")
    @ResponseBody
    public ServerResponse getRoomsParallelHotel(HttpSession session, Integer hotelId,Integer status){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店
            return iHotelService.hotelSetHotelStatus(hotelId,status,user.getId());
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if (iUserService.checkHotelRole(user).isSuccess()){
            //酒店
            String path=request.getSession().getServletContext().getRealPath("upload");
            String targetFileName=iFileService.upload(file,path);
            String url= PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
            Map fileMap= Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerResponse.createBySuccess(fileMap);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
        }

    }

}

