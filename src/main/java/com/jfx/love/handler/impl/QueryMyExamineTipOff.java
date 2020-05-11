package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.Utils.TypeUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.pojo.TipOff;
import com.jfx.love.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QueryMyExamineTipOff implements Handler {
    private static Logger logger= LoggerFactory.getLogger(QueryMyExamineTipOff.class);
    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        List<TipOff> tipOffs = adminService.queryMyExamineTipOff(requestMsg.getAdminId());
        if (tipOffs!=null&&!tipOffs.isEmpty()){
            return JSONArray.toJSONString(new Message(9104,"你正在审核的举报：",tipOffs.size(),tipOffs));
        }
        return JSONArray.toJSONString(new Message(0,"你没有审核举报"));
    }
}
