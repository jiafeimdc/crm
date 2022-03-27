package com.deng.crm.workbench.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WorkbenchIndexController {

    @RequestMapping("/workbench/index.do")
    public String Index(){
      return "workbench/index";
    }

}
