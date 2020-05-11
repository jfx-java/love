package com.jfx.love.controller;

import com.google.gson.Gson;
import com.jfx.love.Utils.IpUtil;
import com.jfx.love.config.ZhenziInfo;
import com.jfx.love.pojo.SmsReturnJson;
import com.zhenzi.sms.ZhenziSmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class ZhenziSmsController {

    private static Logger logger = LoggerFactory.getLogger(ZhenziSmsController.class);

    @Autowired
    @Qualifier("zhenziInfo")
    private ZhenziInfo zhenziInfo;

    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(HttpServletRequest request, String number) {

        try {
            Gson gson = new Gson();
            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com",zhenziInfo.getAppid(),zhenziInfo.getAppSecret());

            Map<String,String> params = new HashMap<String, String>();
            params.put("message","您的验证码为:"+ verifyCode + "，该码有效期为3分钟，该码只能使用一次！来自：爱公益平台");
            params.put("number",number);

            String ip = IpUtil.getIp(request);

//            System.out.println();
            params.put("clientIp",ip);
            String result = client.send(params);
            SmsReturnJson s = gson.fromJson(result, SmsReturnJson.class);
            if(s.getCode() != 0)//发送短信失败
                return "fail";
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是谷歌的gson
            HttpSession session = request.getSession();
            Map map =new HashMap();
            map.put("verifyCode", verifyCode);
            map.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            request.getSession().setAttribute("verifyCode",map);
            logger.info(number+"手机验证码:"+verifyCode);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
