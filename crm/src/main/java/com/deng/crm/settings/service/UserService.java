package com.deng.crm.settings.service;

import com.deng.crm.settings.model.User;

import java.util.Map;

public interface UserService {
    User queryAccount(Map<String,Object> map);
}
