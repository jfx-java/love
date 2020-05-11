package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.Utils.TypeUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;

public class ExamineNoPass implements Handler {

    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        if (adminService.examineNoPass(TypeUtil.objectToInt( requestMsg.getMsg()))){
            return JSONArray.toJSONString(new Message(0,"操作成功!"));
        }
        return JSONArray.toJSONString(new Message(0,"操作失败!(请及时和开发者联系!)"));
    }

}
