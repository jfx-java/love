package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.pojo.TipOff;
import com.jfx.love.service.AdminService;
import com.jfx.love.service.SharerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QueryNewTipOff implements Handler {

    private static Logger logger = LoggerFactory.getLogger(Handler.class);
    private AdminService adminService = SpringUtil.getBean(AdminService.class);

    @Override
    public String handler(RequestMsg requestMsg) {
        List<TipOff> tipOffs = adminService.queryNewAllTipOff();
        if (tipOffs!=null&&tipOffs.size()!=0){
            return JSONArray.toJSONString(new Message(9101,"新的举报信息",tipOffs.size(),tipOffs));
        }
        return JSONArray.toJSONString(new Message(0,"没有新的举报信息"));
    }
}
