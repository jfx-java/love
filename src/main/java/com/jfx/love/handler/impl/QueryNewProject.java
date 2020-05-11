package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QueryNewProject implements Handler {
    private static Logger logger= LoggerFactory.getLogger(QueryNewProject.class);
    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        List<DatabaseRequestProject> databaseRequestProjects = adminService.queryAllNewRequest();
        if (databaseRequestProjects!=null&&databaseRequestProjects.size()!=0){
            logger.info("发送新项目申请"+JSONArray.toJSONString(databaseRequestProjects));
            return JSONArray.toJSONString(new Message(9001,"新的申请项目",databaseRequestProjects.size(),databaseRequestProjects));
        }
        return JSONArray.toJSONString(new Message(0,"没有新的申请项目！"));
    }
}
