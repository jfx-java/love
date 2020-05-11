package com.jfx.love.service;

import com.jfx.love.mapper.DemoMapper;
import com.jfx.love.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 啦啦啦
 * @see System
 * @see IndexOutOfBoundsException
 *
 */
@Service("demoService")
public class DemoService {
    @Autowired
    @Qualifier("demoMapper")
    private DemoMapper demoMapper;
    public List<Demo> selectAll(){
        List<Demo> list = demoMapper.selectAll();
        for (Demo demo:list){
            System.out.println(demo);
        }
        return list;
    }
}
