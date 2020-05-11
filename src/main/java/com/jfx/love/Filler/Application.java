package com.jfx.love.Filler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Application implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration ir1=registry.addInterceptor(new AccepterFiller());
        ir1.addPathPatterns("/seekHelp**");
        ir1.excludePathPatterns();
        registry.addInterceptor(new SharerFiller()).addPathPatterns("/donation**").excludePathPatterns();
        registry.addInterceptor(new AdminFiller()).addPathPatterns("/admin**").excludePathPatterns("/adminLogin");
        //拦截所有//detail**路径，进入SharerFiller过滤器
        registry.addInterceptor(new SharerFiller()).addPathPatterns("/detail**").excludePathPatterns();
//        registry.addInterceptor(new AccepterFiller()).addPathPatterns("/accepter**").excludePathPatterns("/accepterLogin");
//        registry.addInterceptor(new SharerFiller()).addPathPatterns("/sharer**").excludePathPatterns("/sharer/slgn").excludePathPatterns("/sharer/sharerRegister");
//        registry.addInterceptor(new AccepterFiller()).addPathPatterns("/accepter**").excludePathPatterns("/accepter/algn").excludePathPatterns("/accepter/accepterRegister");

    }

}
