package com.jfx.love.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Money {
    private String uuid;
    private Integer projectId;
    private Integer sharerId;
    private BigDecimal money;
    private String tradeNo;
    private Integer state;
    private Date paymentTime;

    public Money(String uuid, Integer projectId, Integer sharerId, BigDecimal money, String tradeNo, Integer state, Date paymentTime) {
        this.uuid = uuid;
        this.projectId = projectId;
        this.sharerId = sharerId;
        this.money = money;
        this.tradeNo = tradeNo;
        this.state = state;
        this.paymentTime = paymentTime;
    }

    public Money() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSharerId() {
        return sharerId;
    }

    public void setSharerId(Integer sharerId) {
        this.sharerId = sharerId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = new BigDecimal(money);
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
}
