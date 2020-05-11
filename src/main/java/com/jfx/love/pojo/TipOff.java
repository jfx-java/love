package com.jfx.love.pojo;

public class TipOff {
    private Integer id;
    private String auditor;
    private Integer sharerId;
    private Integer projectId;
    private String text;
    private String evidence1;
    private String evidence2;
    private String evidence3;
    private String evidence4;
    private Integer state;


    public TipOff(Integer sharerId, Integer projectId, String text) {
        this.sharerId = sharerId;
        this.projectId = projectId;
        this.text = text;
    }

    public TipOff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Integer getSharerId() {
        return sharerId;
    }

    public void setSharerId(Integer sharerId) {
        this.sharerId = sharerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
