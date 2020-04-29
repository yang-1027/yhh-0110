package com.hotel.service;

import com.hotel.common.ServerResponse;
import com.hotel.pojo.User;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-20 22:29
 **/
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> hotelRegister(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> forgetPassword(String username);

    ServerResponse<String> forgetResetPassword(String username,String newPassword,String forgetToken);

    ServerResponse<String> resetPassword(String password,String newPassword,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);

    ServerResponse checkHotelRole(User user);

}

