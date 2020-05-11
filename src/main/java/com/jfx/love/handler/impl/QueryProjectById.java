package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.Utils.TypeUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;

public class QueryProjectById implements Handler {
    private AdminService adminService= SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        DatabaseRequestProject databaseRequestProject = adminService.queryProjectById(TypeUtil.objectToInt(requestMsg.getMsg()));
        if (databaseRequestProject!=null){
            return JSONArray.toJSONString(new Message(9103,"项目详情：",1,databaseRequestProject));
        }
        return JSONArray.toJSONString(new Message(0,"项目不存在!"));
    }
}
