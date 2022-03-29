package com.deng.crm.settings.web.interceptor;

import com.deng.crm.commons.contants.Contants;
import com.deng.crm.settings.model.User;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterpector implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session=httpServletRequest.getSession();
        User user =(User)session.getAttribute(Contants.SESSION_USER);
        if(user==null){
             httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
             return  false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
