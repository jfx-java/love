package com.jfx.love.pojo;

public class Volunteer {
    private Integer id;
    private Integer projectId;
    private Integer sharerId;
    private String sharerName;
    private String sharerTelephone;

    public Volunteer() {
    }

    public Volunteer(Integer id, Integer projectId, Integer sharerId, String sharerName, String sharerTelephone) {
        this.id = id;
        this.projectId = projectId;
        this.sharerId = sharerId;
        this.sharerName = sharerName;
        this.sharerTelephone = sharerTelephone;
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

    public Integer getSharerId() {
        return sharerId;
    }

    public void setSharerId(Integer sharerId) {
        this.sharerId = sharerId;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }

    public String getSharerTelephone() {
        return sharerTelephone;
    }

    public void setSharerTelephone(String sharerTelephone) {
        this.sharerTelephone = sharerTelephone;
    }
}
