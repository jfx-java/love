package com.jfx.love.mapper;

import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.Sharer;
import com.jfx.love.pojo.TipOff;
import com.jfx.love.pojo.Volunteer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
@Repository("sharerMapper")
public interface SharerMapper {
    @Select("SELECT id,name,password,telephone FROM sharer WHERE telephone=#{telephone} AND `password`=#{password}")
    Sharer select(String telephone, String password);
    @Insert("insert INTO sharer(password,telephone) values (#{password},#{telephone})")
    int insert(String telephone,String password);
    @Select("SELECT COUNT(id) FROM sharer WHERE telephone=#{telephone}")
    int selectTelephone(String telephone);




    @Select("SELECT id,auditor,topic,accepter_id,telephone,message,certifier," +
            "Volunteer,now_volunteer,address,money,now_money,is_goods" +
            ",imgurl,evidence1,evidence2,evidence3,evidence4,evidence5," +
            "create_time,end_time FROM request_project WHERE state=2")

    List<DatabaseRequestProject> selectAllRunningProject();

    @Select("SELECT * FROM request_project WHERE id=#{id}")
    DatabaseRequestProject detail(int id);

    @Insert("insert INTO Volunteer(project_id,sharer_id,sharer_name,sharer_telephone) values (#{projectId},#{sharerId},#{sharerName},#{sharerTelephone})")
    int insertVolunteer(Volunteer volunteer);

    @Update("update request_project set now_volunteer=now_volunteer+1 where id = #{id} and volunteer-now_volunteer>0 ")
    int addNowVolunteer(int id);

    @Select("SELECT count(id) FROM volunteer WHERE project_id=#{projectId} and sharer_id=#{sharerId} ")
    int queryVolunteer(int sharerId, int projectId);


    @Insert("insert INTO money(uuid,project_id,sharer_id,money,state) values (#{uuid},#{projectId},#{sharerId},#{money},0)")
    int insertMoney(String uuid, int projectId, int sharerId, BigDecimal money);


    @Update("update money set state=1,trade_no=#{trade_no},payment_time=#{time} where uuid = #{out_trade_no} AND money= #{total_amount}")
    int updataPaySuccessful(String out_trade_no, String total_amount, String trade_no, String time);

    //TODO:是否判断金额到达预期"and money-now_money>0"
    @Update("update request_project set now_money=now_money+#{total_amount} where id=(select project_id from money where uuid=#{out_trade_no}) ")
    int addProjectMoney(String out_trade_no, String total_amount);

    @Select("select money-now_money from request_project where id=(select project_id from money where uuid=#{out_trade_no})")
    BigDecimal checkUp(String out_trade_no);

    @Update("UPDATE  request_project set state=3 where state=2 and( end_time - curdate() <= 0 );")
    int updataExpireProject();

    @Update("update request_project set state=3 where id=(select project_id from money where uuid=#{out_trade_no})and money-now_money<=0")
    int projectEnd(String out_trade_no);

    @Select("select volunteer-now_volunteer from request_project where id = #{projectId}")
    int checkVolunteerNum(int projectId);


    @Insert("insert INTO tip_off (sharer_id,project_id,text,evidence1,evidence2,evidence3,evidence4)values(#{sharerId},#{projectId},#{text},#{evidence1},#{evidence2},#{evidence3},#{evidence4})")
    int insertTipOff(TipOff tipOff);


    @Select("select money-now_money from request_project where id = #{projectId} ")
    BigDecimal checkMoneyNum(int projectId);


    @Select("select money,volunteer,is_goods from request_project " +
            "where id=(select project_id from money where uuid=#{out_trade_no})")
    DatabaseRequestProject queryProjectByMoneyUUID(String out_trade_no);

    @Select("select money,volunteer,is_goods from request_project where id =#{projectId}")
    DatabaseRequestProject queryProjectById(Integer projectId);


    @Update("update request_project set state=7 where id=#{projectId}")
    int projectEndById(Integer projectId);
}
