package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.Utils.TypeUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;

/**
 * 审核
 */
public class ToExamine implements Handler {
    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {

   if (adminService.toExamine(requestMsg.getAdminId(), TypeUtil.objectToInt(requestMsg.getMsg()))){
            return JSONArray.toJSONString(new Message(0,"请认真负责审核！"));
        }
        return JSONArray.toJSONString(new Message(0,"该项目已经有人进行审核了！"));
    }
}
