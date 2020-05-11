package com.jfx.love.pojo;

public class VolunteerExcel {
    private String sharerName;
    private String sharerTelephone;

    public VolunteerExcel(String sharerName, String sharerTelephone) {
        this.sharerName = sharerName;
        this.sharerTelephone = sharerTelephone;
    }

    public VolunteerExcel() {
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
