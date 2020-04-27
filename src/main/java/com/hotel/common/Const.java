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

    public enum RoomStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        RoomStatusEnum(int code,String value){
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

    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未付款"),
        PAID(20,"已付款"),
        SHIPPED(40,"已入住"),
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭");

        OrderStatusEnum(int code,String value){
            this.value=value;
            this.code=code;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for (OrderStatusEnum orderStatusEnum:values()){
                if (orderStatusEnum.getCode()==code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }

    }
    public interface AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY="WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS="TRADE_SUCCESS";

        String RESPONSE_SUCCESS="success";
        String RESPONSE_FAILED="failed";

    }

    public enum PayPlatformEnum{

        APIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.value=value;
            this.code=code;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.value=value;
            this.code=code;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }
        public int getCode() {
            return code;
        }

        public static PaymentTypeEnum codeOf(int code){
            for (PaymentTypeEnum paymentTypeEnum:values()){
                if (paymentTypeEnum.getCode()==code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }
}

