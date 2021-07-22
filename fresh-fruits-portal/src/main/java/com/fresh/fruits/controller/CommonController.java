package com.fresh.fruits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
}
