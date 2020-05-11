package com.jfx.love.Filler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccepterFiller implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object ob=session.getAttribute("accepter");
        if (ob!=null) {
            return true;
        }
        session.setAttribute("preurl",request.getRequestURI());
        StringBuffer url = request.getRequestURL();
        String tempContextUrl ="/accepterLogin";
        response.sendRedirect(tempContextUrl);
        return false;
    }
}
