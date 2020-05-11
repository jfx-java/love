package com.jfx.love.pojo;

public class RequestMsg {
    private String adminId;
    private String code;
    private Object msg;

    public RequestMsg(String adminId, String code, Object msg) {
        this.adminId = adminId;
        this.code = code;
        this.msg = msg;
    }

    public RequestMsg(String adminId, Object msg) {
        this.adminId = adminId;
        this.msg = msg;
    }

    public RequestMsg() {
    }


    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
