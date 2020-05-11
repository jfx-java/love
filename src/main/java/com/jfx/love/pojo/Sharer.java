package com.jfx.love.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Sharer {
    private int id;
    private String name;
    private String password;
    private String telephone;
    private int loveNumber;
    private String imgurl;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public Sharer(String telephone,String password) {
        this.password = password;
        this.telephone = telephone;
    }

    public Sharer() {
    }

    @Override
    public String toString() {
        return "Sharer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", loveNumber=" + loveNumber +
                ", imgurl='" + imgurl + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(int loveNumber) {
        this.loveNumber = loveNumber;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
