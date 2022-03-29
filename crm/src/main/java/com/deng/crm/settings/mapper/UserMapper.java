package com.deng.crm.settings.mapper;

import com.deng.crm.settings.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User queryAccount(Map<String,Object> map);

    List<User> selectAllUser();

}