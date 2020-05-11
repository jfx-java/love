package com.jfx.love.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.controller.WebSocketServer;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.Message;
import com.jfx.love.service.AdminService;
import com.jfx.love.service.SharerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 到达end时间的项目，自动进入结项状态
 */

public class DatabaseProjectEndScanner {
    private Logger logger = LoggerFactory.getLogger(DatabaseProjectEndScanner.class);

    private SharerService sharerService;
    private static DatabaseProjectEndScanner instance;
    //线程池(延迟)
    private ScheduledExecutorService scheduleService = null;
    private static DatabaseProjectEndScanner databaseScanner;
    private Gson gson = new GsonBuilder().create();


    public static final synchronized DatabaseProjectEndScanner getInstance() {
        if (instance == null) {
            instance = new DatabaseProjectEndScanner();
        }
        return instance;
    }

    public DatabaseProjectEndScanner() {
        sharerService= SpringUtil.getBean(SharerService.class);
    }


    public final void start() {
        if (scheduleService==null){
        scheduleService = Executors.newScheduledThreadPool(1);}
        run();
    }

    private void run() {

        logger.info("过期项目自动处理器开启");

        scheduleService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //查询所有正在运行的项目
                int i = sharerService.updataExpireProject();
                logger.info("自动处理过期项目，进入结项状态项目数：" + i);
            }

        },getInitialDelay() , 24 * 60 * 60, TimeUnit.MINUTES);
    }

    //停止扫描器
    public final void stop() {
        logger.info("过期项目自动处理器开启");
        scheduleService.shutdown();
    }

    //算出距离24点05分还有多少分钟
    public long getInitialDelay() {
        long now = System.currentTimeMillis();
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        long overTime = 0;
        try {
            //24点05分
            overTime = 86405 - ((now - (sdfOne.parse(sdfOne.format(now)).getTime())) / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return overTime / 60;
    }
}
