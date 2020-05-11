package com.jfx.love.Utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    public static String getIp(HttpServletRequest  request){
        String ip =null;
        try {
            ip=request.getHeader("x-forwarded-for");
            //equalsIgnoreCase不区分大小写
            if (ip==null|| ip.length()==0||"unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip==null|| ip.length()==0||"unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip==null|| ip.length()==0||"unknown".equalsIgnoreCase(ip)){
                ip=request.getRemoteAddr();
                if(ip.equals("127.0.0.1")){
                    InetAddress inet=null;
                    try {
                        inet=InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ip=inet.getHostAddress();
                }
            }
            if(ip != null && ip.length() > 15){ // "***.***.***.***".length()
                if (ip.indexOf(",")>0){
                    ip=ip.substring(0,ip.indexOf(","));
                }
            }
            if (ip.equals("0:0:0:0:0:0:0:1")){
                ip="221.215.133.186";
            }
        } catch (Exception e) {
            ip="";
        }
        return ip;

    }
}
