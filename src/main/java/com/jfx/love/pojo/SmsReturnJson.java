package com.jfx.love.pojo;

public class SmsReturnJson {
    private int code;
    private String data;

    public SmsReturnJson(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SmsReturnJson{" +
                "code=" + code +
                ", data='" + data + '\'' +
                '}';
    }

    public SmsReturnJson() {
    }
}
