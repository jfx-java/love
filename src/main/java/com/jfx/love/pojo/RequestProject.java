package com.jfx.love.pojo;



import java.util.Date;

public class RequestProject {
    private Integer id;
    private String topic;
    private String accepterId;
    private String telephone;
    private String message;
    //格式："证明人姓名:电话;"
    private String certifier;
    //志愿者
    private String volunteer;
    private String address;
    //单位元
    private String money;
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
    private Date createTime;
    private String endTime;

    public RequestProject() {
    }

    public RequestProject(Integer id, String topic, String accepterId, String telephone, String message, String certifier, String volunteer, String address, String money, String alipayNum, Integer isGoods, String goods, String imgurl, String evidence1, String evidence2, String evidence3, String evidence4, String evidence5, Date createTime, String endTime) {
        this.id = id;
        this.topic = topic;
        this.accepterId = accepterId;
        this.telephone = telephone;
        this.message = message;
        this.certifier = certifier;
        this.volunteer = volunteer;
        this.address = address;
        this.money = money;
        this.alipayNum = alipayNum;
        this.isGoods = isGoods;
        this.goods = goods;
        this.imgurl = imgurl;
        this.evidence1 = evidence1;
        this.evidence2 = evidence2;
        this.evidence3 = evidence3;
        this.evidence4 = evidence4;
        this.evidence5 = evidence5;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RequestProject{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", accepterId='" + accepterId + '\'' +
                ", telephone='" + telephone + '\'' +
                ", Message='" + message + '\'' +
                ", certifier='" + certifier + '\'' +
                ", Volunteer='" + volunteer + '\'' +
                ", address='" + address + '\'' +
                ", money='" + money + '\'' +
                ", alipayNum='" + alipayNum + '\'' +
                ", isGoods=" + isGoods +
                ", goods='" + goods + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", evidence1='" + evidence1 + '\'' +
                ", evidence2='" + evidence2 + '\'' +
                ", evidence3='" + evidence3 + '\'' +
                ", evidence4='" + evidence4 + '\'' +
                ", evidence5='" + evidence5 + '\'' +
                ", createTime=" + createTime +
                ", endTime='" + endTime + '\'' +
                '}';
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

    public String getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(String accepterId) {
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

    public String getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
