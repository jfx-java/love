package com.jfx.love.pojo;

public class Admin {
    private Integer id;
    private String name;
    private String password;
    private Integer power;

    public Admin() {
    }

    public Admin(Integer id, String name, String password, Integer power) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.power = power;
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
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
