package com.hotel.common;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-21 08:55
 **/
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证json序列化时，如果是null对象，key会消失
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T date;

    private ServerResponse(int status){
        this.status=status;
    }

    private ServerResponse(int status, T date){
        this.status=status;
        this.date=date;
    }

    private ServerResponse(int status, String msg, T date){
        this.status=status;
        this.date=date;
        this.msg=msg;
    }

    private ServerResponse(int status, String msg){
        this.status=status;
        this.msg=msg;
    }

    @JsonIgnore
    //使之不再json序列化中
    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getDate() {
        return date;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T date){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),date);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T date){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,date);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMsg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMsg);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMsg){
        return new ServerResponse<T>(errorCode,errorMsg);
    }
}

