package com.jfx.love.mapper;

import com.jfx.love.pojo.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository("demoMapper")
public interface DemoMapper {
    @Select("select * from demo")
    List<Demo> selectAll();
}
