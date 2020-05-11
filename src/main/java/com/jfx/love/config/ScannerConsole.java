package com.jfx.love.config;

import org.springframework.stereotype.Component;

@Component
public class ScannerConsole {
    public static Boolean isScannerRunning=false;
    //初始延迟 默认2秒
    public static Long initialDelay=2L;
    //期间延迟 默认十分钟
    public static Long period=600L;

}
