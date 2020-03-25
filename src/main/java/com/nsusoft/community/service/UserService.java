package com.nsusoft.community.service;

import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public void createOrUpdate(User user) {
        User dbUser = mapper.queryByAccountId(user.getAccountId());
        if (dbUser == null) {
            //数据库中不存在User, 插入User
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            mapper.insert(user);
        } else {
            //数据库中存在User, 更新User
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(user.getGmtCreate());
            dbUser.setPhotoUrl(user.getPhotoUrl());
            mapper.update(dbUser);
        }
    }
}
