package com.fuzy.myshiro.controller;

import com.fuzy.myshiro.pojo.User;
import com.fuzy.myshiro.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fuzy
 * create time 18-11-9-下午4:28
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping
    public String login(String username, String password) {
        User user = userService.findByName(username);
        if (user.getPassword().equals(password)) return "success";
        return "failed";
    }
}
