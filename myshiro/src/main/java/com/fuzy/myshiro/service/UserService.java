package com.fuzy.myshiro.service;

import com.fuzy.myshiro.pojo.User;

/**
 * @author fuzy
 * create time 18-11-9-下午4:38
 */
public interface UserService {

    User findByName(String name);
}
