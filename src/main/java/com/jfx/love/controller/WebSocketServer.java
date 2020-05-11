package com.jfx.love.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.jfx.love.Utils.ClassUtil;

import com.jfx.love.Utils.StringUtils;

import com.jfx.love.config.GlobalConfig;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imserver/{userId}")
@Component("webSocketServer")
public class WebSocketServer {
    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);


    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static  int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接成功调用的方法
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            //如果已经有了这个id，就先删除
            webSocketMap.remove(userId);
            //然后再添加id，value为当前对象
            webSocketMap.put(userId, this);
        } else {
            webSocketMap.put(userId, this);

            //在线数加1
            addOnlineCount();
        }

        logger.info("用户连接：" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("用户:" + userId + ",网络异常!!!!!!");
            ;
        }

    }


    /**
     * 连接关闭时调用此方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            //删除id
            webSocketMap.remove(userId);
            subOnlineCount();
        }
        logger.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到消息！ 管理员id:" + userId + ",报文:" + message);

        //可以群发信息；
        //信息保存到数据库中，redis
        if (StringUtils.isNotBlank(message)) {
            try {

                //解析接收的的报文
                String code = JSON.parseObject(message).getString("code");

                Handler handler = getBean(GlobalConfig.protocolMap.get(code));
                String msg = handler.handler(new RequestMsg(userId, code,JSON.parseObject(message).getString("msg")));
                logger.info("发送给管理员id:" + userId + "   报文:" + msg);

                //回复应答
                if (msg != null && msg.length() > 0) {
                    sendMessage(msg);
                }


//                if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
//                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
//
//                } else {
//                    logger.error("请求的userId:" + toUserId + "不在服务器上");
//                    //否则不再这个服务器上，发送到mysql或者redis
//                }

            } catch (Exception e) {
                try {
                    sendMessage(JSONArray.toJSONString(new Message(0, "服务器异常！")));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生异常后调用的方法
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:" + this.userId + "原因:" + error.getMessage());
        error.printStackTrace();
    }


    /**
     * 实现服务器主动推送
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        logger.info("发送信息到:" + userId + ",报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            logger.error("用户:" + userId + "不在线！");
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static synchronized Enumeration<String> getOnlineId() {
        return webSocketMap.keys();
    }


    private Handler getBean(String className) {
        return (Handler) ClassUtil.getBean(className);
    }

}
