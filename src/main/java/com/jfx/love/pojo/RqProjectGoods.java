package com.jfx.love.pojo;

public class RqProjectGoods {
    private Integer id;
    private Integer projectId;
    private String needGoodsName;
    private Integer needGoodsNum;
    private Integer nowNum;

    public RqProjectGoods() {
    }

    public RqProjectGoods(Integer id, Integer projectId, String needGoodsName, Integer needGoodsNum, Integer nowNum) {
        this.id = id;
        this.projectId = projectId;
        this.needGoodsName = needGoodsName;
        this.needGoodsNum = needGoodsNum;
        this.nowNum = nowNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getNeedGoodsName() {
        return needGoodsName;
    }

    public void setNeedGoodsName(String needGoodsName) {
        this.needGoodsName = needGoodsName;
    }

    public Integer getNeedGoodsNum() {
        return needGoodsNum;
    }

    public void setNeedGoodsNum(Integer needGoodsNum) {
        this.needGoodsNum = needGoodsNum;
    }

    public Integer getNowNum() {
        return nowNum;
    }

    public void setNowNum(Integer nowNum) {
        this.nowNum = nowNum;
    }
}
