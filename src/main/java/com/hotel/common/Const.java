package com.hotel.common;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-21 15:48
 **/
public class Const {

    public static final String CURRENT_USER="currentUser";

    public static final String USERNAME="username";

    public static final String EMAIL="email";

    public interface Role{
        int ROLE_CUSTOMER=2;
        int ROLE_HOTEL=1;
        int ROLE_ADMIN=0;
    }
}

