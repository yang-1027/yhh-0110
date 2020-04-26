package com.hotel.common;

import com.google.common.collect.Sets;

import java.util.Set;

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

    public interface hotelListOrderBy{
        Set<String> STAR_ASC_DESC= Sets.newHashSet("star_asc","star_desc");

    }

    public interface Role{
        int ROLE_CUSTOMER=2;
        int ROLE_HOTEL=1;
        int ROLE_ADMIN=0;
    }
    public enum HotelStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        HotelStatusEnum(int code,String value){
            this.code=code;
            this.value=value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}

