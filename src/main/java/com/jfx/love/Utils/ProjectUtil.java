package com.jfx.love.Utils;

import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.RequestProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ProjectUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static DatabaseRequestProject upRProjectToDProject(RequestProject requestProject){
        DatabaseRequestProject drp=new DatabaseRequestProject(requestProject.getTopic(),requestProject.getTelephone(),requestProject.getMessage(),requestProject.getCertifier(),requestProject.getAddress(),requestProject.getAlipayNum(),requestProject.getGoods());
        try {
            drp.setEndTime(simpleDateFormat.parse(requestProject.getEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return drp;
    }
}
