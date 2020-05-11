package com.jfx.love.mapper;

import com.jfx.love.pojo.Accepter;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.VolunteerExcel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("accepterMapper")
public interface AccepterMapper {
    @Select("SELECT id,name,password,telephone FROM accepter WHERE telephone=#{telephone} AND `password`=#{password}")
    Accepter select(String telephone, String password);
    @Insert("insert INTO accepter(name,password,telephone) values (#{name},#{password},#{telephone})")
    int insert(String telephone,String name,String password);
    @Select("SELECT COUNT(id) FROM accepter WHERE telephone=#{telephone}")
    int selectTelephone(String telephone);

    /**
     * 存储 申请项目
     * @param project
     * @return
     */
    @Insert("insert INTO request_project (topic,accepter_id,telephone,Message," +
            "certifier,Volunteer,address,money,alipay_num,is_goods,imgurl,evidence1," +
            "evidence2,evidence3,evidence4,evidence5,end_time)" +
            " values (#{topic},#{accepterId},#{telephone},#{message},#{certifier}," +
            "#{volunteer},#{address},#{money},#{alipayNum},#{isGoods},#{imgurl},#{evidence1},#{evidence2}," +
            "#{evidence3},#{evidence4},#{evidence5},#{endTime})")
    int insertRequestProject(DatabaseRequestProject project);

    @Select("SELECT id FROM request_project WHERE imgurl=#{imgurl}")
    int imgurlQueryProjectId(String imgurl);

    @Insert("insert INTO rq_project_goods (project_id,need_goods_name," +
            "need_goods_num)values(#{projectId},#{needGoodsName},#{needGoodsNum})")
    int insertRqProjectGoods(int projectId, String needGoodsName, int needGoodsNum);


    @Select("SELECT * from request_project WHERE accepter_id = #{id}")
    List<DatabaseRequestProject> queryProjectByAccepterId(int id);


    @Select("SELECT sharer_name,sharer_telephone from volunteer where project_id=#{id}")
    List<VolunteerExcel> queryVolunteerByProjectId(Integer id);
}
