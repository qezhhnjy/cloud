package com.fuzy.myshiro.service.impl;

import com.fuzy.myshiro.MyshiroApplication;
import com.fuzy.myshiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = MyshiroApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void findByName() {
        userService.findByName("fuzy");
    }
}