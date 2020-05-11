package com.jfx.love.service;

import com.jfx.love.Utils.FileUtils;
import com.jfx.love.Utils.ProjectUtil;
import com.jfx.love.config.GlobalConfig;
import com.jfx.love.mapper.AccepterMapper;
import com.jfx.love.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("accepterService")
@Transactional
public class AccepterService {
    @Autowired
    @Qualifier("accepterMapper")
    private AccepterMapper accepterMapper;
    @Autowired
    @Qualifier("globalConfig")
    private GlobalConfig globalConfig;


    public Boolean keepRequestProject(RequestProject project, MultipartFile img, MultipartFile files[]) throws Exception {
        Integer accepterId = Integer.valueOf(project.getAccepterId());
        DatabaseRequestProject drp = ProjectUtil.upRProjectToDProject(project);
        drp.setAccepterId(accepterId);

        drp = keepFilesToLocad(drp, files);
        drp = keepImgToLocad(drp, img);
        String goods = project.getGoods();
        int isGoods = 0;
        if (!goods.equals(":;") && goods.length() != 2) {
            isGoods = 1;
        }
        drp.setIsGoods(isGoods);
        String volunt = project.getVolunteer();
        if (volunt != null && !volunt.equals("") && volunt.length() != 0 && !volunt.equals("无")) {
            Integer volunteer = Integer.valueOf(volunt);
            drp.setVolunteer(volunteer);
        }
        String mon = project.getMoney();
        if (mon != null && !mon.equals("") && mon.length() != 0) {
            drp.setMoney(mon);
            drp.setAlipayNum(project.getAlipayNum());
        }

        return keepRProject(drp);
    }

    public Boolean keepRProject(DatabaseRequestProject drp){
        boolean is = true;

        int i = accepterMapper.insertRequestProject(drp);
        if (drp.getIsGoods() == 1) {
            String goods = drp.getGoods();
            //用uuid的身份证合照查询projectId
            int projectId = accepterMapper.imgurlQueryProjectId(drp.getImgurl());
            for (String a : goods.split(";")) {
                String[] split = a.split(":");
                int n = accepterMapper.insertRqProjectGoods(projectId, split[0], Integer.valueOf(split[1]));
                if (n != 1) {
                    is = false;
                    break;
                }
            }
        }
        if (i == 1 && is) {
            return true;
        }

        return false;

    }


    public DatabaseRequestProject keepImgToLocad(DatabaseRequestProject project, MultipartFile img) throws Exception {
        project.setImgurl(FileUtils.uploadPicture(img, globalConfig.getFilePath()));
        return project;
    }

    public DatabaseRequestProject keepFilesToLocad(DatabaseRequestProject project, MultipartFile imgs[]) throws Exception {

        String filePath = globalConfig.getFilePath();
        int length = imgs.length;
        switch (length) {
            case 1:
                project.setEvidence1(FileUtils.uploadPicture(imgs[0], filePath));
                break;
            case 2:
                project.setEvidence1(FileUtils.uploadPicture(imgs[0], filePath));
                project.setEvidence2(FileUtils.uploadPicture(imgs[1], filePath));
                break;
            case 3:
                project.setEvidence1(FileUtils.uploadPicture(imgs[0], filePath));
                project.setEvidence2(FileUtils.uploadPicture(imgs[1], filePath));
                project.setEvidence3(FileUtils.uploadPicture(imgs[2], filePath));
                break;
            case 4:
                project.setEvidence1(FileUtils.uploadPicture(imgs[0], filePath));
                project.setEvidence2(FileUtils.uploadPicture(imgs[1], filePath));
                project.setEvidence3(FileUtils.uploadPicture(imgs[2], filePath));
                project.setEvidence4(FileUtils.uploadPicture(imgs[3], filePath));
                break;
            case 5:
                project.setEvidence1(FileUtils.uploadPicture(imgs[0], filePath));
                project.setEvidence2(FileUtils.uploadPicture(imgs[1], filePath));
                project.setEvidence3(FileUtils.uploadPicture(imgs[2], filePath));
                project.setEvidence4(FileUtils.uploadPicture(imgs[3], filePath));
                project.setEvidence5(FileUtils.uploadPicture(imgs[4], filePath));
                break;
        }

        return project;

    }


    public Accepter login(String telephone, String password) {
        return accepterMapper.select(telephone, password);

    }

    public boolean register(String telephone, String name, String password) {
        int i = accepterMapper.insert(telephone, name, password);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public boolean selectTelephone(String telephone) {
        int i = accepterMapper.selectTelephone(telephone);
        if (i == 0) {
            return true;
        }
        return false;
    }


    public List<DatabaseRequestProject> queryMySeekHelp(int id) {
        return accepterMapper.queryProjectByAccepterId(id);
    }

    /**
     * 查询出该项目的所有志愿者名单
     * @param id
     * @return
     */
    public List<VolunteerExcel> queryVolunteerByProjectId(Integer id) {
        return accepterMapper.queryVolunteerByProjectId(id);
    }
}
