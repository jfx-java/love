package com.jfx.love.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseRequestProject {


//    static {
//        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    }
//    private static SimpleDateFormat simpleDateFormat;
    private Integer id;
    private String topic;
    private String auditor;
    private Integer accepterId;
    private String telephone;
    private String message;
    //格式："证明人姓名:电话;"
    private String certifier;
    //志愿者
    private Integer volunteer;
    private Integer nowVolunteer;
    private String address;
    //单位元 默认0元
    private BigDecimal money=new BigDecimal(0.00);
    private BigDecimal nowMoney;
    private String alipayNum;
    //是否有物品
    private Integer isGoods;
    private String goods;
    //证明图片1
    private String imgurl;
    private String evidence1;
    private String evidence2;
    private String evidence3;
    private String evidence4;
    private String evidence5;
    private int state;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public DatabaseRequestProject() {
    }

    public DatabaseRequestProject(String topic, String telephone, String message, String certifier, String address, String alipayNum, String goods) {
        this.topic = topic;
        this.telephone = telephone;
        this.message = message;
        this.certifier = certifier;
        this.address = address;
        this.alipayNum = alipayNum;
        this.goods = goods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Integer getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(Integer accepterId) {
        this.accepterId = accepterId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCertifier() {
        return certifier;
    }

    public void setCertifier(String certifier) {
        this.certifier = certifier;
    }

    public Integer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Integer volunteer) {
        this.volunteer = volunteer;
    }

    public Integer getNowVolunteer() {
        return nowVolunteer;
    }

    public void setNowVolunteer(Integer nowVolunteer) {
        this.nowVolunteer = nowVolunteer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = new BigDecimal(money);
    }

    public BigDecimal getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(BigDecimal nowMoney) {
        this.nowMoney = nowMoney;
    }

    public String getAlipayNum() {
        return alipayNum;
    }

    public void setAlipayNum(String alipayNum) {
        this.alipayNum = alipayNum;
    }

    public Integer getIsGoods() {
        return isGoods;
    }

    public void setIsGoods(Integer isGoods) {
        this.isGoods = isGoods;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getEvidence1() {
        return evidence1;
    }

    public void setEvidence1(String evidence1) {
        this.evidence1 = evidence1;
    }

    public String getEvidence2() {
        return evidence2;
    }

    public void setEvidence2(String evidence2) {
        this.evidence2 = evidence2;
    }

    public String getEvidence3() {
        return evidence3;
    }

    public void setEvidence3(String evidence3) {
        this.evidence3 = evidence3;
    }

    public String getEvidence4() {
        return evidence4;
    }

    public void setEvidence4(String evidence4) {
        this.evidence4 = evidence4;
    }

    public String getEvidence5() {
        return evidence5;
    }

    public void setEvidence5(String evidence5) {
        this.evidence5 = evidence5;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public DatabaseRequestProject(Integer id, String topic, String auditor, Integer accepterId, String telephone, String message, String certifier, Integer volunteer, Integer nowVolunteer, String address, BigDecimal money, BigDecimal nowMoney, String alipayNum, Integer isGoods, String goods, String imgurl, String evidence1, String evidence2, String evidence3, String evidence4, String evidence5, int state, Date applyTime, Date createTime, Date endTime) {
        this.id = id;
        this.topic = topic;
        this.auditor = auditor;
        this.accepterId = accepterId;
        this.telephone = telephone;
        this.message = message;
        this.certifier = certifier;
        this.volunteer = volunteer;
        this.nowVolunteer = nowVolunteer;
        this.address = address;
        this.money = money;
        this.nowMoney = nowMoney;
        this.alipayNum = alipayNum;
        this.isGoods = isGoods;
        this.goods = goods;
        this.imgurl = imgurl;
        this.evidence1 = evidence1;
        this.evidence2 = evidence2;
        this.evidence3 = evidence3;
        this.evidence4 = evidence4;
        this.evidence5 = evidence5;
        this.state = state;
        this.applyTime = applyTime;
        this.createTime = createTime;
        this.endTime = endTime;
    }
}
