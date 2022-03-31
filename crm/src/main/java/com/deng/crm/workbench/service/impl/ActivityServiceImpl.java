package com.deng.crm.workbench.service.impl;

import com.deng.crm.workbench.mapper.ActivityMapper;
import com.deng.crm.workbench.model.Activity;
import com.deng.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("activityService")  //传入其接口的名字，前面习惯于小写
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveActivity(Activity activity) {
          return activityMapper.saveActivity(activity);
    }

    @Override
    public List<Activity> selectActivityByPage(Map<String, Object> map) {
        return activityMapper.selectActivityByPage(map);
    }

    @Override
    public int selectCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }
}
