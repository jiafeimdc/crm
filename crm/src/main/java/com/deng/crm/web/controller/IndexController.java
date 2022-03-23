package com.deng.crm.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String Index(){
        return "index";
    }

//    @RequestMapping("/settings/qx/user/toLogin.do")
//    public String toLogin(){
//        return "settings/qx/user/login";
//    }
}
