package com.jfx.love.pojo;

public class Message {
    private int code;
    private String msg;
    //list的长度
    private int dataLength;
    private Object data;

    public Message() {
    }

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(int code, String msg, int dataLength, Object data) {
        this.code = code;
        this.msg = msg;
        this.dataLength = dataLength;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
