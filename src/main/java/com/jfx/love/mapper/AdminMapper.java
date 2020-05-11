package com.jfx.love.mapper;

import com.jfx.love.pojo.Admin;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.pojo.RqProjectGoods;
import com.jfx.love.pojo.TipOff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Mapper
@Repository(value = "adminMapper")
public interface AdminMapper {
    @Select("SELECT COUNT(id) FROM request_project WHERE state = 0")
    int queryRequestProject();

    //查询待审核的新申请项目
    @Select("SELECT * FROM request_project WHERE state = 0")
    List<DatabaseRequestProject> queryAll();
    @Select("SELECT id,name,password FROM admin WHERE (name=#{name} AND password=#{password})")
    Admin login(Admin admin);


    //TODO:查询所有举报
    @Select("select id,sharer_id,project_id,text,evidence1,evidence2,evidence3,evidence4 from tip_off where state=0")
    List<TipOff> selectAllTipOff();


    @Select("SELECT * FROM rq_project_goods WHERE (project_id=#{projectId})")
    List<RqProjectGoods> queryRqGoodsByid(int projectId);


    /**
     * 防止多人重复审核，审核WHERE id=#{id} and state=0。当返回值为0时，为已经被别人审核了
     * @param adminId
     * @param id
     * @return
     */
    @Update("UPDATE request_project SET state=1,auditor=#{adminId} WHERE id=#{id} and state=0")
    int toExamine(String adminId,int id);


    @Update("UPDATE request_project SET state=0,auditor=null WHERE id=#{id}")
    int cancelExamine(int id);

    @Select("SELECT * FROM request_project WHERE (auditor=#{adminId})")
    List<DatabaseRequestProject> queryProjectByAdminId(String adminId);


    @Update("UPDATE request_project SET state=2,create_time= #{createTime} WHERE id=#{id}")
    int examinePass(int id, Date createTime);

    @Update("UPDATE request_project SET state=4 WHERE id=#{id}")
    int examineNoPass(int id);


    @Select("select count(id) from tip_off where state = 0 ")
    int queryNewTipOff();

    @Update("update tip_off set state=1,auditor=${auditor} where id=#{id}")
    int examineTipOffById(int id,String auditor);


    @Select("SELECT id,sharer_id,project_id,text,evidence1,evidence2,evidence3,evidence4,state from tip_off where auditor=${auditor}")
    List<TipOff> queryTipOffByAuditor(@Param("auditor") String auditor);

    @Select("SELECT * from request_project where id=#{id}")
    DatabaseRequestProject queryProjectById(@Param("id") Integer id);

    @Update("UPDATE tip_off set state=#{state} where id=#{id} ")
    int changeTipOffState(@Param("id") Integer tipOffId,@Param("state") Integer state);

    @Update("UPDATE request_project set state=#{state} where id =(select project_id from tip_off where id=#{id})")
    int changeProjectState(@Param("id")Integer tipOffId,@Param("state")Integer state);

}
