package com.jfx.love;

import com.jfx.love.config.DatabaseProjectEndScanner;
import com.jfx.love.config.GlobalConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.jfx.love.mapper")
public class LoveApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoveApplication.class, args);
        //初始化全局配置
        GlobalConfig.initSystem();
        //启动数据库自动结项扫描器
        DatabaseProjectEndScanner.getInstance().start();

    }


}
