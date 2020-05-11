package com.jfx.love.controller;


import com.jfx.love.pojo.Sharer;
import com.jfx.love.pojo.Volunteer;
import com.jfx.love.service.SharerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/sharer")
public class SharerController {
    @Autowired
    @Qualifier("sharerService")
    private SharerService sharerService;

    @ResponseBody
    @PostMapping("/slgn")
    public Map<String,Object> Login(@Param("checkCode") String checkCode,HttpServletRequest req, Sharer sharer){

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
        Sharer share = sharerService.login(sharer.getTelephone(), sharer.getPassword());
        if (share!=null){
            login=true;
            HttpSession session = req.getSession();
            session.removeAttribute("sharerRegisterSuccess");
            session.setAttribute("sharer",share);
        }
        map.put("msg",msg);
        map.put("isLoginSuccess",login);
        return map;
    }
    @ResponseBody
    @PostMapping("/sharerRegister")
    public Map<String,Object> register(HttpServletRequest req , @Param("telephone") String telephone, String password
            , String qrpassword,@Param("verifyCode") String verifyCode,@Param("checkCode")String checkCode){
        Map<String,Object> resultMap = new HashMap<>();
        Boolean isRegisterSuccess=false;
        String msg = "";
        resultMap.put("isSuccess",true);
        if (!qrpassword.equals(password)){
            msg="两次密码不一致！";
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);
            return resultMap;
        }
        HttpSession session = req.getSession();
        Map map= (Map)session.getAttribute("verifyCode");
        String s = (String)map.get("verifyCode");

        if (!verifyCode.toLowerCase().equals(s)){
            msg="手机验证码输入错误！";
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);
            return resultMap;
        }

        if (!checkCode.toLowerCase().equals(session.getAttribute("randomImageStr").toString())){
            msg="图片验证码输入错误！";
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);
            return resultMap;
        }

        if (!sharerService.selectTelephone(telephone)){
            msg="手机号已被注册过！";
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);
            return resultMap;
        }

        if (sharerService.register(telephone, password)){
            isRegisterSuccess=true;
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);
            session.setAttribute("sharerRegisterSuccess",new Sharer(telephone,password));
            return resultMap;
        };
        resultMap.put("isRegisterSuccess",isRegisterSuccess);
        resultMap.put("msg",msg);
        return resultMap;
    }




    /**
     * 查看是否捐款是否已经超出预期
     * @param projectId
     * @param projectId
     * @return
     */
    @ResponseBody
    @PostMapping("/queryMoneyBeyond")
    public Map<String,Object> queryMoneyBeyond(int projectId){
        Map<String,Object> map = new HashMap<>();
        Boolean isSuccess=true;
        Boolean isContribution=false;
        if (sharerService.checkMoneyNum(projectId)) {
            isContribution=true;
        }
        map.put("isSuccess",isSuccess);
        map.put("isContribution",isContribution);
        return map;
    }





    /**
     * 查看是否已经报过名
     * @param sharerId
     * @param projectId
     * @return
     */
    @ResponseBody
    @PostMapping("/queryVolunteer")
    public Map<String,Object> queryVolunteer(int sharerId,int projectId){
        Map<String,Object> map = new HashMap<>();
        Boolean isSuccess=false;
        Boolean isSignUp=false;
        if (sharerService.checkVolunteerNum(projectId)) {
            isSuccess=true;
            if (sharerService.queryVolunteer(sharerId, projectId)) {
                isSignUp = true;
            }
        }
        map.put("isSuccess",isSuccess);
        map.put("isSignUp",isSignUp);
        return map;
    }



    //报名
    @ResponseBody
    @PostMapping("/signUp")
    public Map<String,Object> signUp(Volunteer volunteer){
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",true);
        Boolean isSigUpSuccess=false;
        if (sharerService.signUp(volunteer)){
            isSigUpSuccess=true;
        }
        map.put("isSigUpSuccess",isSigUpSuccess);
        return map;
    }

    @PostMapping("/tipOffJump")
    public ModelAndView tipOffJump(int projectId){
        ModelAndView mv = new ModelAndView("report/tip_off");
        mv.addObject("projectId",projectId);
        return mv;
    }

    @ResponseBody
    @PostMapping("/tipOff")
    public Map<String,Boolean> tipOff(String sharerId,String projectId,String text,
                                      @RequestParam(value = "files") MultipartFile[] files){
        Map<String,Boolean> map = new HashMap<>();
        map.put("isSuccess",true);
        if (files.length>4){
            map.put("isSuccess",false);
            return map;
        }
        Boolean isUpSuccess =sharerService.tipOff(sharerId,projectId,text,files);
        map.put("isUpSuccess",isUpSuccess);
        return map;
    }
}
