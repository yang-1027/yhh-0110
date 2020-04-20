package com.hotel.pojo;

import java.util.Date;

public class Collect {
    private Integer id;

    private Integer userId;

    private Integer roomId;

    private Integer hotelId;

    private Integer quantity;

    private Integer checked;

    private Date createTime;

    private Date updateTime;

    public Collect(Integer id, Integer userId, Integer roomId, Integer hotelId, Integer quantity, Integer checked, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.quantity = quantity;
        this.checked = checked;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Collect() {
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
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