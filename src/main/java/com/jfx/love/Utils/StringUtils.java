package com.jfx.love.Utils;

public class StringUtils {
    public static boolean isNotBlank(String message) {
        return message!=null&&!message.equals("")&&message.length()!=0;
    }
}
