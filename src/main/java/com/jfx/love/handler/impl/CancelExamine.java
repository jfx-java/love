package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;
//取消审核
public class CancelExamine implements Handler {
    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        if (adminService.cancelExamine(Integer.valueOf((String) requestMsg.getMsg()))){
            return JSONArray.toJSONString(new Message(0,"取消成功"));
        }
        return JSONArray.toJSONString(new Message(0,"取消失败"));
    }
}
