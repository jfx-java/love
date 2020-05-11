package com.jfx.love.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfx.love.controller.WebSocketServer;

import com.jfx.love.pojo.Message;
import com.jfx.love.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Enumeration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



@Component
public class DatabaseScanner {
    private Logger logger = LoggerFactory.getLogger(DatabaseScanner.class);
    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;
    private static DatabaseScanner instance;
    //线程池(延迟)
    private ScheduledExecutorService scheduleService = null;
    private static DatabaseScanner databaseScanner;
    private Gson gson = new GsonBuilder().create();


    //依赖注入完成后运行
    @PostConstruct
    public void init() {
        databaseScanner = this;
        databaseScanner.adminService= this.adminService;
    }

    public final Boolean start(){
        if (!ScannerConsole.isScannerRunning) {
            scheduleService = Executors.newScheduledThreadPool(1);
            run();
            ScannerConsole.isScannerRunning = true;
        }
        return ScannerConsole.isScannerRunning;

    }

    private void run() {

        logger.info("数据库扫描器开启");

        scheduleService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //查询数据库的project申请情况，提示管理员前去审核

                int i = adminService.querNewProject();

                if (i>0){
                    //通知在线的管理员(分配问题)
                    //TODO:分配问题（当基金会规模扩大时，管理员连线信息将用redis数据库存储，id-职务，查询空闲管理员，后续待解决问题）
                    Enumeration<String> onlineId = WebSocketServer.getOnlineId();
                    while (onlineId.hasMoreElements()) {
                        String s = onlineId.nextElement();
                        if (s != null) {
                            try {
                                //发送json
                                WebSocketServer.sendInfo(gson.toJson(new Message(0, "有新的项目申请，请尽快前去审核")), s);
                                logger.info("向审核员id为：" + s + " 发送新项目信息");
                            } catch (IOException e) {
                                logger.error("后台推送新项目扫描信息失败");
                            }
                        }
                    }
                }
                //分配两个线程做这两个事？
                int j = adminService.queryNewTipOff();
                if (j>0){
                    //TODO:分配问题（当基金会规模扩大时，管理员连线信息将用redis数据库存储，id-职务，查询空闲管理员，后续待解决问题）
                    //目前先都发
                    Enumeration<String> onlineId = WebSocketServer.getOnlineId();
                    while (onlineId.hasMoreElements()){
                        String s = onlineId.nextElement();
                        if (s!=null) {
                            try {
                                //发送json
                                WebSocketServer.sendInfo(gson.toJson(new Message(0,"有新的举报，请尽快前去审核")),s);
                                logger.info("向审核员id为："+ s +" 发送新举报信息");
                            } catch (IOException e) {
                                logger.error("后台推送新举报扫描信息失败");
                            }
                        }
                    }
                }
            }
        }, ScannerConsole.initialDelay, ScannerConsole.period, TimeUnit.SECONDS);

    }
    //停止扫描器
    public final boolean stop() {
        logger.info("数据库扫描器关闭");
        ScannerConsole.isScannerRunning = false;
        Boolean b=false;
        try {
            if (scheduleService != null)
                scheduleService.shutdown();
            b=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
