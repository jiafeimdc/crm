package com.deng.crm.workbench.mapper;

import com.deng.crm.workbench.model.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {

    int saveActivity(Activity activity);

    List<Activity> selectActivityByPage(Map<String,Object> map);

    /**
     * 根据条件查询市场活动的总条数
     * @param map
     * @return
     */
    int selectCountOfActivityByCondition(Map<String,Object> map);
}
