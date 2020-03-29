package com.nsusoft.community.service;

import com.nsusoft.community.entity.User;
import com.nsusoft.community.entity.UserExample;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper mapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = mapper.selectByExample(userExample);
        //User dbUser = mapper.queryByAccountId(user.getAccountId());
        if (users.size() == 0) {
            //数据库中不存在User, 插入User
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            mapper.insert(user);
        } else {
            //数据库中存在User, 更新User
            User updateUser = new User();
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setGmtModified(user.getGmtCreate());
            updateUser.setPhotoUrl(user.getPhotoUrl());

            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(users.get(0).getId());

            mapper.updateByExampleSelective(updateUser, example);
            //mapper.update(dbUser);
        }
    }
}
