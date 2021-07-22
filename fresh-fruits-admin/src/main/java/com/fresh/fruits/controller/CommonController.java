package com.fresh.fruits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    /**
     * 通用的控制器跳转
     *
     *    page_index -----> index.html
     *    page_tbContent -----> tbContent.html
     *    page_tbContentCategory -----> tbContentCategory.html
     *    page_tbItemCat -----> tbItemCat.html
     *    page_welcome -----> welcome.html
     * @param p
     * @return
     */
    @RequestMapping("/page_{xx}")
    public String topage(@PathVariable("xx") String p){
        return p;
    }
}
