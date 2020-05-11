package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;

import java.util.List;

public class QueryNowExamine implements Handler {

    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        List<DatabaseRequestProject> databaseRequestProjects = adminService.queryNowExamine(requestMsg.getAdminId());
        if (databaseRequestProjects!=null&&!databaseRequestProjects.isEmpty()){
            return JSONArray.toJSONString(new Message(9004,"你正在审核的项目：",databaseRequestProjects.size(),databaseRequestProjects));
        }
        return JSONArray.toJSONString(new Message(0,"你没有进行审核的项目！"));
    }
}
