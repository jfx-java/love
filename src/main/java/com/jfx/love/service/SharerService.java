package com.jfx.love.service;

import com.jfx.love.Utils.FileUtils;
import com.jfx.love.config.GlobalConfig;
import com.jfx.love.mapper.AdminMapper;
import com.jfx.love.mapper.SharerMapper;
import com.jfx.love.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("sharerService")
public class SharerService {

    @Autowired
    @Qualifier("sharerMapper")
    private SharerMapper sharerMapper;


    @Autowired
    @Qualifier("globalConfig")
    private GlobalConfig globalConfig;

    @Autowired
    @Qualifier("adminMapper")
    private AdminMapper adminMapper;

    public Sharer login(String telephone, String password) {
        return sharerMapper.select(telephone, password);
    }

    public boolean register(String telephone, String password) {
        int i = sharerMapper.insert(telephone, password);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public boolean selectTelephone(String telephone) {
        int i = sharerMapper.selectTelephone(telephone);
        if (i == 0) {
            return true;
        }
        return false;
    }

    public List<DatabaseRequestProject> queryAllRunningProject() {

//        Map map=new HashMap();

//        map.put("projects",databaseRequestProjects);


        return sharerMapper.selectAllRunningProject();
    }

    public Map<String, Object> detail(int id) {
        Map map = new HashMap();
        DatabaseRequestProject detail = sharerMapper.detail(id);
        //code: 0捐款类，1捐物类 2报名类
        String code = "";
        List<RqProjectGoods> rqProjectGoods = null;
        if (detail.getMoney().intValue() != 0) {
            code = code + "0";
        }
        if (detail.getIsGoods() != 0) {
            code = code + "1";
            rqProjectGoods = adminMapper.queryRqGoodsByid(id);
        }
        if (detail.getVolunteer() != null && detail.getVolunteer() != 0) {
            code = code + "2";
        }
        map.put("code", code);
        map.put("project", detail);

        map.put("goods", rqProjectGoods);
        return map;
    }

    public Boolean signUp(Volunteer volunteer) {
        int i1 = sharerMapper.addNowVolunteer(volunteer.getProjectId());
        if (i1 != 1) {
            return false;
        }
        int i = sharerMapper.insertVolunteer(volunteer);


        //判断报名后是否到达预期，到达预期如果为 只报名类项目，则自动关闭；
        if (sharerMapper.checkVolunteerNum(volunteer.getProjectId())<=0){
            DatabaseRequestProject databaseRequestProject = sharerMapper.queryProjectById(volunteer.getProjectId());
            String projectType = getProjectType(databaseRequestProject);

            if (projectType.equals("2")){
                //结项完毕，将状态设置为7
                sharerMapper.projectEndById(volunteer.getProjectId());
            }
        }


        if (i == 1 && i1 == 1) {
            return true;
        }
        return false;
    }


    public Boolean queryVolunteer(int sharerId, int projectId) {
        int i = sharerMapper.queryVolunteer(sharerId, projectId);

        if (i == 0) {
            return true;
        }
        return false;
    }

    /**
     * 保存订单
     *
     * @param projectId
     * @param sharerId
     * @param money
     * @param uuid
     * @return
     */
    public Boolean saveOrder(int projectId, int sharerId, BigDecimal money, String uuid) {
        int i = sharerMapper.insertMoney(uuid, projectId, sharerId, money);
        if (i == 1) {
            return true;
        }
        return false;
    }


    private String getProjectType(DatabaseRequestProject detail) {
        //code: 0捐款类，1捐物类 2报名类
        String code = "";
        if (detail.getMoney().intValue() != 0) {
            code = code + "0";
        }
        if (detail.getIsGoods() != 0) {
            code = code + "1";
        }
        if (detail.getVolunteer() != null && detail.getVolunteer() != 0) {
            code = code + "2";
        }
        return code;
    }

    /**
     * 判断是否支付成功
     *
     * @param out_trade_no
     * @param total_amount
     * @param trade_no
     * @param time
     * @return
     */
    public Boolean paySuccessful(String out_trade_no, String total_amount, String trade_no, String time) {

        int i = sharerMapper.updataPaySuccessful(out_trade_no, total_amount, trade_no, time);
        int i1 = sharerMapper.addProjectMoney(out_trade_no, total_amount);
        DatabaseRequestProject databaseRequestProject = sharerMapper.queryProjectByMoneyUUID(out_trade_no);
        //判断是否为纯捐钱项目，为纯捐钱项目则查询是否到达资金预期，到达则关闭项目
        String projectType = getProjectType(databaseRequestProject);
        if (projectType.equals("0")){
            //如money-now_money<=0 自动结项  状态设置为3 等待结项中
            sharerMapper.projectEnd(out_trade_no);

        }

        if (i == 1 && i1 == 1) {
            return true;
        }
        return false;
    }
    public int updataExpireProject() {
        return sharerMapper.updataExpireProject();
    }

    public boolean checkVolunteerNum(int projectId) {
        int i = sharerMapper.checkVolunteerNum(projectId);
        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean tipOff(String sharerId, String projectId, String text, MultipartFile[] files) {
        TipOff tipOff = new TipOff(Integer.valueOf(sharerId), Integer.valueOf(projectId), text);


        try {
            tipOff = keepFiles(files, tipOff);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int i = sharerMapper.insertTipOff(tipOff);
        if (i == 1) {
            return true;
        }
        return false;
    }

    private TipOff keepFiles(MultipartFile[] files, TipOff tipOff) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String tipOffImgPath = globalConfig.getTipOffImgPath();

        int i = 1;

        for (MultipartFile file : files) {
            Method method = tipOff.getClass().getMethod("setEvidence" + (i++), String.class);
            method.invoke(tipOff, FileUtils.uploadPicture(file, tipOffImgPath));
        }

        return tipOff;

    }

    public boolean checkMoneyNum(int projectId) {
        BigDecimal bigDecimal = sharerMapper.checkMoneyNum(projectId);
        BigDecimal bd = new BigDecimal(0);
        if (bigDecimal.compareTo(bd) == 1) {
            return true;
        }
        return false;
    }
}
