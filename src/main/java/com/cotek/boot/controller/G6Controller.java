package com.cotek.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fuzy
 * create time 19-1-8-下午5:08
 */
@Controller
@RequestMapping("/g6")
public class G6Controller {

    @RequestMapping
    public String index() {
        return "g6";
    }
}
