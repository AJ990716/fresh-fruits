package com.fresh.fruits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CommonController {

    @RequestMapping("/")
    public String toSearh(){
        return "search";
    }
}
