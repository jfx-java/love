package com.jfx.love.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("zhenziInfo")
public class ZhenziInfo {
    @Value("${Zhenzi.appid}")
    private String appid;
    @Value("${Zhenzi.appSecret}")
    private String appSecret;
    @Value("${Zhenzi.apiUrl}")
    private String apiURL;

    public String getAppid() {
        return appid;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
