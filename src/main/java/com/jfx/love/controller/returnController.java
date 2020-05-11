package com.jfx.love.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/return")
public class returnController {
    @GetMapping("/demo")
    public void retn(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aaa");

    }
}
