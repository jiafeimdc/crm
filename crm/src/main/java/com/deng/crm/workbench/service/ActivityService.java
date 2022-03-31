package com.deng.crm.workbench.service;

import com.deng.crm.workbench.model.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    int saveActivity(Activity activity);

    List<Activity> selectActivityByPage(Map<String,Object> map);


    int selectCountOfActivityByCondition(Map<String,Object> map);

    int delectActivity(int id);
}
