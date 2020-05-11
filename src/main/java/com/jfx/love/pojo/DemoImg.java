package com.jfx.love.pojo;

public class DemoImg {
    private int id;
    private String imgurl1;
    private String imgurl2;
    private String imgurl3;
    private String imgurl4;
    private String imgurl5;

    @Override
    public String toString() {
        return "DemoImg{" +
                "id=" + id +
                ", imgurl1='" + imgurl1 + '\'' +
                ", imgurl2='" + imgurl2 + '\'' +
                ", imgurl3='" + imgurl3 + '\'' +
                ", imgurl4='" + imgurl4 + '\'' +
                ", imgurl5='" + imgurl5 + '\'' +
                '}';
    }

    public DemoImg() {
    }

    public DemoImg(int id, String imgurl1, String imgurl2, String imgurl3, String imgurl4, String imgurl5) {
        this.id = id;
        this.imgurl1 = imgurl1;
        this.imgurl2 = imgurl2;
        this.imgurl3 = imgurl3;
        this.imgurl4 = imgurl4;
        this.imgurl5 = imgurl5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl1() {
        return imgurl1;
    }

    public void setImgurl1(String imgurl1) {
        this.imgurl1 = imgurl1;
    }

    public String getImgurl2() {
        return imgurl2;
    }

    public void setImgurl2(String imgurl2) {
        this.imgurl2 = imgurl2;
    }

    public String getImgurl3() {
        return imgurl3;
    }

    public void setImgurl3(String imgurl3) {
        this.imgurl3 = imgurl3;
    }

    public String getImgurl4() {
        return imgurl4;
    }

    public void setImgurl4(String imgurl4) {
        this.imgurl4 = imgurl4;
    }

    public String getImgurl5() {
        return imgurl5;
    }

    public void setImgurl5(String imgurl5) {
        this.imgurl5 = imgurl5;
    }
}
