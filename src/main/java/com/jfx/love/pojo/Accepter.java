package com.jfx.love.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.Objects;

public class Accepter {
    private int id;
    private String name;
    private String password;
    private String telephone;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accepter accepter = (Accepter) o;
        return id == accepter.id &&
                name.equals(accepter.name) &&
                password.equals(accepter.password) &&
                telephone.equals(accepter.telephone) &&
                createDate.equals(accepter.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, telephone, createDate);
    }

    public Accepter() {
    }

    public Accepter(String name, String password, String telephone) {
        this.name = name;
        this.password = password;
        this.telephone = telephone;
    }

    public Accepter(int id, String name, String password, String telephone, Date createDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.createDate = createDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
