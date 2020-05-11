package com.jfx.love.Utils;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.pojo.Message;

public class JsonUtil {
    public static String messageToJson(Message message){
        return JSONArray.toJSONString(message);
    }
}
