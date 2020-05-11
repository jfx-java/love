package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.config.DatabaseScanner;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StopScanner implements Handler {
    private static Logger logger= LoggerFactory.getLogger(StopScanner.class);
    private static DatabaseScanner databaseScanner= SpringUtil.getBean(DatabaseScanner.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        if (databaseScanner.stop()){
            logger.info("扫描器关闭");
            return JSONArray.toJSONString(new Message(0,"关闭成功"));

        }
        logger.warn("扫描器关闭失败");
        return JSONArray.toJSONString(new Message(0,"关闭失败"));
    }
}
