package com.cotek.boot.controller;

import com.cotek.boot.prop.Girl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qezhhnjy
 */
@RestController
public class FirstController {

    @Resource
    private Girl girl;

    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private int age;

    @Value("${content}")
    private String content;

    @RequestMapping("/")
    public String hello() {
        return "Hello My First Spring Boot!";
    }

    @GetMapping("/girl/{name}")
    public String girl(@PathVariable("name") String name) {
        return girl.toString() + " name : " + name;
//        return content;
//        return "cupSize : " + cupSize + "<br/> age : " + age;
    }


    @GetMapping({"/say/{id}", "/{id}/say"})
    public String say(@PathVariable("id") String id) {
        return "id : " + id;
    }

    @GetMapping("/say")
    public String say2(@RequestParam("id") Integer id) {
        return "2-id : " + id;
    }
}
