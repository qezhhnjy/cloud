package com.fuzy.myshiro.service.impl;

import com.fuzy.myshiro.dao.UserMapper;
import com.fuzy.myshiro.pojo.User;
import com.fuzy.myshiro.pojo.UserExample;
import com.fuzy.myshiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fuzy
 * create time 18-11-9-下午4:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(name);
        return userMapper.selectByExample(example).get(0);
    }

}
