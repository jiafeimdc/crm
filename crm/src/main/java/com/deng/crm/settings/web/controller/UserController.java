package com.deng.crm.settings.web.controller;


import com.deng.crm.commons.contants.Contants;
import com.deng.crm.commons.domain.ReturnObject;
import com.deng.crm.commons.utils.DataUtils;
import com.deng.crm.settings.model.User;
import com.deng.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }



    @RequestMapping("/settings/qx/user/login.do")
    public  @ResponseBody  Object login(String loginAct, String loginPwd, String isRem, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service层方法
        User user=null;
        try {
            user = userService.queryAccount(map);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        ReturnObject returnObject=new ReturnObject();
        if(user==null){
            //登录失败  账号密码错误
            returnObject.setCode("0");
            returnObject.setMessage("账户或者密码错误");
        }else{
            //判断判断账号是否合法
            //  user.getExpireTime();//得到该用户的过期时间

            String nowStr= DataUtils.formateDateTime(new Date());//得到现在的时间
            if(nowStr.compareTo(user.getExpireTime())>0){  //如果现在的时间大于用户过期的时间
                //登录失败，账户已经过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账户已经过期");
            }else if("0".equals(user.getLockState())){ //判断该账户是否已经被锁定
                //已经被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账户已经被锁定");
            }else if(user.getAllowIps().contains(request.getRemoteAddr())){  //如果远程ip不在用户的Ip里面
                // 登录失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else{
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("登录成功");
                session.setAttribute(Contants.SESSION_USER,user);

                //如果需要记住密码，则往外写cookie
                if("true".equals(isRem)){
                    Cookie c1=new Cookie("loginAct",user.getLoginAct());
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd",user.getLoginPwd());
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else{
                    //把没有过期cookie删除
                    Cookie c1=new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie c1=new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2=new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        //跳转到首页
        return "redirect:/";
    }



    @ResponseBody
    @PostMapping("/settings/qx/user/test.do")
    public  Object login2() {
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", "ls");
        map.put("loginPwd", "yf123");
        //调用service层方法
        User user = userService.queryAccount(map);
        return user;
    }


}
