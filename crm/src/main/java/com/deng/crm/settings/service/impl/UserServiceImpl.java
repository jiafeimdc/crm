package com.deng.crm.settings.service.impl;

import com.deng.crm.settings.mapper.UserMapper;
import com.deng.crm.settings.model.User;
import com.deng.crm.settings.service.UserService;
import com.sun.scenario.effect.impl.prism.PrImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryAccount(Map<String, Object> map) {
         return  userMapper.queryAccount(map);
    }

    @Override
    public List<User> selectAllUser() {
       return userMapper.selectAllUser();
    }
}
