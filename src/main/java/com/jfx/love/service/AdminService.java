package com.jfx.love.service;

import com.jfx.love.mapper.AdminMapper;
import com.jfx.love.pojo.Admin;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.RqProjectGoods;
import com.jfx.love.pojo.TipOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;

@Service("adminService")
@Transactional
public class AdminService {
    @Autowired
    @Qualifier("adminMapper")
    private AdminMapper adminMapper;


    private static Logger logger = LoggerFactory.getLogger(AdminService.class);

    public int querNewProject() {
        return adminMapper.queryRequestProject();
    }

    public int queryNewTipOff() {
        return adminMapper.queryNewTipOff();
    }


    public List<TipOff> queryNewAllTipOff(){
        return adminMapper.selectAllTipOff();
    }


    public List<DatabaseRequestProject> queryAllNewRequest() {
        return adminMapper.queryAll();
    }

    public Admin login(Admin admin) {

        return adminMapper.login(admin);
    }

    public List<RqProjectGoods> queryRqGoodsByid(int id) {
        return adminMapper.queryRqGoodsByid(id);
    }

    public Boolean toExamine(String adminId, int id) {

        int i = adminMapper.toExamine(adminId, id);
        if (i == 1) {
            return true;
        }

        return false;
    }

    public List<DatabaseRequestProject> queryNowExamine(String adminId) {
        return adminMapper.queryProjectByAdminId(adminId);
    }

    public Boolean cancelExamine(int id) {
        int i = adminMapper.cancelExamine(id);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public Boolean examinePass(int id) {
        Date date = new Date(System.currentTimeMillis());

        int i = adminMapper.examinePass(id, date);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public Boolean examineNoPass(int id) {
        int i = adminMapper.examineNoPass(id);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public Boolean examineTipOffById(int id,String auditor){
        int i = adminMapper.examineTipOffById(id, auditor);
        if (i==1){
            return true;
        }
        return false;
    }


    public List<TipOff> queryMyExamineTipOff(String auditor) {
        return adminMapper.queryTipOffByAuditor(auditor);
    }

    public DatabaseRequestProject queryProjectById(Integer id) {
        return adminMapper.queryProjectById(id);
    }

    public Boolean examineTipOffPass(Integer tipOffId) {
        //先修改举报状态为举报属实状态
        //再修改项目状态为暂停状态
        int i1 = adminMapper.changeTipOffState(tipOffId, 2);
        logger.info("changeTipOffState受影响行数："+i1);
        int i = adminMapper.changeProjectState(tipOffId, 5);
        logger.info("changeProjectState受影响行数："+i);
        if (i1==1&&i==1){
        return true;}
        return false;
    }

    public boolean examineTipOffNoPass(Integer tipOffId) {
        int i = adminMapper.changeTipOffState(tipOffId, 3);
        if (i==1){
            return true;
        }
        return false;
    }
}
