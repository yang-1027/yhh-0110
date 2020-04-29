package com.hotel.pojo;

import java.util.Date;

public class Detils {
    private Integer id;

    private Integer userId;

    private Integer hotelId;

    private String stayName;

    private Integer stayPhone;

    private String  stayCardId;

    private Date createTime;

    private Date updateTime;

    public Detils(Integer id, Integer userId, Integer hotelId, String stayName, Integer stayPhone, String stayCardId, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.stayName = stayName;
        this.stayPhone = stayPhone;
        this.stayCardId = stayCardId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Detils() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getStayName() {
        return stayName;
    }

    public void setStayName(String stayName) {
        this.stayName = stayName == null ? null : stayName.trim();
    }

    public Integer getStayPhone() {
        return stayPhone;
    }

    public void setStayPhone(Integer stayPhone) {
        this.stayPhone = stayPhone;
    }

    public String getStayCardId() {
        return stayCardId;
    }

    public void setStayCardId(String stayCardId) {
        this.stayCardId = stayCardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}