package com.jfx.love.controller;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfx.love.Utils.FileUtils;
import com.jfx.love.pojo.*;
import com.jfx.love.service.AccepterService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/accepter")
public class AccepterController {
    @Autowired
    @Qualifier("accepterService")
    private AccepterService accepterService;

    @Autowired
    @Qualifier("webSocketServer")
    private WebSocketServer webSocketServer;
    private Gson gson = new GsonBuilder().create();

    private Logger logger = LoggerFactory.getLogger(AccepterController.class);

    @ResponseBody
    @PostMapping("/algn")
    public Map<String,Object> Login(@Param("checkCode") String checkCode,HttpServletRequest req, Accepter accepter){

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
        Accepter accept = accepterService.login(accepter.getTelephone(), accepter.getPassword());

        if (accept!=null){
            HttpSession session = req.getSession();
            session.removeAttribute("accepterRegisterSuccess");
            session.setAttribute("accepter",accept);
            login=true;
        }
        map.put("msg",msg);
        map.put("isLoginSuccess",login);
        return map;
    }
    @ResponseBody
    @PostMapping("/accepterRegister")
    public Map<String,Object> register(HttpServletRequest req , @Param("name") String name,@Param("telephone") String telephone, String password
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

        if (!accepterService.selectTelephone(telephone)){
            msg="手机号已被注册过！";
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);
            return resultMap;
        }

        if (accepterService.register(telephone,name, password)){
            isRegisterSuccess=true;
            resultMap.put("isRegisterSuccess",isRegisterSuccess);
            resultMap.put("msg",msg);

            session.setAttribute("accepterRegisterSuccess",new Accepter(name,password,telephone));
            return resultMap;
        };
        resultMap.put("isRegisterSuccess",isRegisterSuccess);
        resultMap.put("msg",msg);
        return resultMap;
    }

    @ResponseBody
    @PostMapping("/upRequestProject")
    public Map upRequestProject(RequestProject project,
                                @RequestParam(value = "img") MultipartFile img ,
                                @RequestParam(value = "files")MultipartFile[] files
                                ){
        Map map=new HashedMap();
        map.put("isSuccess",true);
        int length =files.length;
        if (length>5){
            map.put("isSuccess",false);
            return map;
        }
        Boolean isUpSuccess=false;
        try {
            isUpSuccess= accepterService.keepRequestProject(project,img,files);
            notice();

        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("isUpSuccess",isUpSuccess);
        return map;
    }

    @Async
    public void  notice(){
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

    @GetMapping("/mySeekHelp")
    public String mySeekHelpModel(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn,int id) {
        //当前页数，每页数量
        PageHelper.startPage(pn, 3);
        List<DatabaseRequestProject> databaseRequestProjects = accepterService.queryMySeekHelp(id);
        PageInfo pageInfo = new PageInfo(databaseRequestProjects);
        if (databaseRequestProjects==null||databaseRequestProjects.isEmpty()) {
            return "seekHelp/notHave";
        }
        model.addAttribute("pageInfo", pageInfo);
        return "seekHelp/mySeekHelp";
    }



    @PostMapping("/queryVolunteer")
    public String queryVolunteer(Integer id, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",true);
        List<VolunteerExcel> volunteer = accepterService.queryVolunteerByProjectId(id);
        FileUtils.volunteersToExcel(volunteer,"志愿者名单.xls",response);
        return "下载";
    }




}
