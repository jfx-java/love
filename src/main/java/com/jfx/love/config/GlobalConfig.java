package com.jfx.love.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component("globalConfig")
public class GlobalConfig {




    @Value("${path.imagesPath}")
    private String filePath;



    @Value("${path.tipOffImgPath}")
    private String tipOffImgPath;

    @Autowired
    @Qualifier("zhenziInfo")
    private ZhenziInfo zhenziInfo;

    private static ZhenziInfo zz;

    @PostConstruct
    public void _init(){
        zz=zhenziInfo;
    }

//    public static void demo(){
//        System.out.println(zz.getApiURL());
//    }



    public static HashMap<String, String> protocolMap = new HashMap<>();

    public String getFilePath() {
        return filePath;
    }

    public String getTipOffImgPath() {
        return tipOffImgPath;
    }
    public static void initSystem() {

        initSendProtocolMap();

    }


    public static void initSendProtocolMap() {
        protocolMap.put("0000", "com.jfx.love.handler.impl.StartScanner");
        protocolMap.put("0001", "com.jfx.love.handler.impl.StopScanner");
        protocolMap.put("1001", "com.jfx.love.handler.impl.QueryNewProject");
        protocolMap.put("1002", "com.jfx.love.handler.impl.ToExamine");
        protocolMap.put("1003", "com.jfx.love.handler.impl.QueryGoodsById");
        protocolMap.put("1004", "com.jfx.love.handler.impl.QueryNowExamine");
        protocolMap.put("1010", "com.jfx.love.handler.impl.CancelExamine");
        protocolMap.put("1011", "com.jfx.love.handler.impl.ExaminePass");
        protocolMap.put("1019", "com.jfx.love.handler.impl.ExamineNoPass");
        protocolMap.put("1101", "com.jfx.love.handler.impl.QueryNewTipOff");
        protocolMap.put("1102", "com.jfx.love.handler.impl.ExamineTipOff");
        protocolMap.put("1103", "com.jfx.love.handler.impl.QueryProjectById");
        protocolMap.put("1104", "com.jfx.love.handler.impl.QueryMyExamineTipOff");
        protocolMap.put("1111", "com.jfx.love.handler.impl.ExamineTipOffPass");
        protocolMap.put("1119", "com.jfx.love.handler.impl.ExamineTipOffNoPass");

    }
}
