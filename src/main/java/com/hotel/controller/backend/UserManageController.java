package com.hotel.controller.backend;

import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.User;
import com.hotel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-21 21:54
 **/
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User>login(String username, String password, HttpSession session){
        ServerResponse<User> response =iUserService.login(username,password);
        if (response.isSuccess()){
            User user=response.getDate();
            if (user.getRole()==Const.Role.ROLE_ADMIN){
                //说明是管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else {
                ServerResponse.createByErrorMessage("该用户不是管理员，无法登陆");
            }
        }
        return response;
    }
}

