package com.deng.crm.workbench.web.controller;

import com.deng.crm.commons.contants.Contants;
import com.deng.crm.commons.domain.ReturnObject;
import com.deng.crm.commons.utils.DataUtils;
import com.deng.crm.commons.utils.UUIDUtils;
import com.deng.crm.settings.model.User;
import com.deng.crm.settings.service.UserService;
import com.deng.crm.workbench.model.Activity;
import com.deng.crm.workbench.service.ActivityService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityContorller {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
     public String index(HttpServletRequest request){
        List<User> users = userService.selectAllUser();
        //把数据保存到reqiest中
        request.setAttribute("userList",users);
        //请求转发
        return "workbench/activity/index";
    }


    @RequestMapping("/workbench/activity/save.do")
    public @ResponseBody Object saveActivity(Activity activity, HttpSession session){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DataUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());//这里保存用户的id
        //调用service方法
        ReturnObject returnObject=new ReturnObject();

        try{
            int ret=activityService.saveActivity(activity);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("创建成功！！！！！！！");
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("加入失败！！！！！");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统报错！！！！！"+ex.getMessage());
        }
        return returnObject;
    }



}
