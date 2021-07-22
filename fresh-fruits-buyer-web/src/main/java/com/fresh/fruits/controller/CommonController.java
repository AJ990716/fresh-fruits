package com.fresh.fruits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits.controller;
 * @author: Administrator;
 * @date: 2021/7/8 23:42;
 * @Description:
 */
@Controller
public class CommonController {
    //跳转注册页面
    @RequestMapping("/")
    public String toRegister(){
        return "register";
    }
}
