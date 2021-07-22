package com.fresh.fruits.controller;

import com.fresh.fruits.api.BuyerApi;
import com.fresh.fruits.pojo.BuyerEntity;
import com.fresh.fruits.utils.DataUtils;
import com.fresh.fruits.utils.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits.controller;
 * @author: Administrator;
 * @date: 2021/7/8 23:29;
 * @Description:
 */
@Controller
@RequestMapping("buyer")
@Slf4j
public class BuyerController {
    @Autowired
    BuyerApi buyerApi;

    @GetMapping("list")
    @ResponseBody
    public R list(){
        Map<String, Object> params = new HashMap();
        return buyerApi.list(params);
    }

    @ApiOperation(value = "会员注册",notes = "会员注册...")
    @ApiImplicitParam(name = "buyer",value = "注册对象")
    @PostMapping("register")
    @ResponseBody
    public R register(BuyerEntity buyer){
        //设置ID
        buyer.setCreateTime(DataUtils.getDate());
        buyer.setId(buyer.getNickname());
        buyer.setStatus("0");
        log.info("要注册的对象是："+buyer);
        return buyerApi.save(buyer);
    }
}
