package com.hotel.pojo;

import java.util.Date;

public class Hotels {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer status;

    private String mainImage;

    private String subImages;

    private String detail;

    private Integer sortOrder;

    private Float star;

    private Date createTime;

    private Date updateTime;

    public Hotels(Integer id, String name, Integer userId, Integer status, String mainImage, String subImages, String detail, Integer sortOrder, Float star, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.status = status;
        this.mainImage = mainImage;
        this.subImages = subImages;
        this.detail = detail;
        this.sortOrder = sortOrder;
        this.star = star;

        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Hotels() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() { return status;}

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
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