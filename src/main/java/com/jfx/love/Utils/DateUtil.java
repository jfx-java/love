package com.jfx.love.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
        static {
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    private static SimpleDateFormat simpleDateFormat;
    public static String date2String(Date date){
        if (date!=null){
        return simpleDateFormat.format(date);}
        return null;
    }
}
