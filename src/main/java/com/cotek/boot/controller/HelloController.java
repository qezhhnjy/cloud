package com.cotek.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试Thymeleaf模板的功能，不引入Thymeleaf时，这里会递归进入这个hello路由
 * @author qezhhnjy
 */
@Controller
public class HelloController {

    @GetMapping("/hello_girl")
    public String hello() {
        return "hello";
    }

    @GetMapping("/girl")
    public String girl() {
        return "girl";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/turn")
    public String turn() {
        return "turn";
    }
}
