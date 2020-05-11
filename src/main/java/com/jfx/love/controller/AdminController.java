package com.jfx.love.controller;


import com.jfx.love.pojo.Admin;
import com.jfx.love.pojo.Sharer;
import com.jfx.love.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;

    @ResponseBody
    @PostMapping("/algn")
    public Map<String,Object> Login(@Param("checkCode") String checkCode,HttpServletRequest req, Admin admin){

        Map<String,Object> map=new HashMap<>();
        map.put("isSuccess",true);
        boolean login=false;
        String msg="手机号或密码有误，请检查后重新输入！";
        if (!req.getSession().getAttribute("randomImageStr").toString().equals(checkCode.toLowerCase())){
            msg="验证码输入有误！";
            map.put("isLoginSuccess",login);
            map.put("msg",msg);
            return map;
        }
        Admin ad = adminService.login(admin);
        if (ad!=null){
            login=true;
            HttpSession session = req.getSession();
            session.setAttribute("admin",ad);
//            int sessionTimeout = session.getServletContext().getSessionTimeout();
//            System.err.println(sessionTimeout);
//            System.err.println(sessionTimeout);
//            System.err.println(sessionTimeout);
//            System.err.println(sessionTimeout);
        }
        map.put("msg",msg);
        map.put("isLoginSuccess",login);
        return map;
    }

}
