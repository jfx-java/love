package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.Utils.TypeUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamineTipOff implements Handler {
    private static Logger logger= LoggerFactory.getLogger(ExamineTipOff.class);
    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        logger.info("管理员id:"+requestMsg.getAdminId()+"  进行举报审核，举报id:"+TypeUtil.objectToInt(requestMsg.getMsg()));
        if (adminService.examineTipOffById(TypeUtil.objectToInt(requestMsg.getMsg()),requestMsg.getAdminId())){
            logger.info("管理员id:"+requestMsg.getAdminId()+"  进行举报审核成功，举报id:"+TypeUtil.objectToInt(requestMsg.getMsg()));
            return JSONArray.toJSONString(new Message(0,"进行审核成功，请认真负责审核！"));
        }
            return JSONArray.toJSONString(new Message(0,"进行审核失败，请及时联系管理员！"));


    }
}
