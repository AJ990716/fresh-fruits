package com.fresh.fruits.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.fruits.pojo.BuyerAuthsEntity;
import com.fresh.fruits.service.BuyerAuthsService;
import com.fresh.fruits.utils.PageUtils;
import com.fresh.fruits.utils.R;



/**
 * 登录授权
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:21
 */
@RestController
@RequestMapping("buyerauths")
public class BuyerAuthsController {
    @Autowired
    private BuyerAuthsService buyerAuthsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok().put("page", null);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		BuyerAuthsEntity buyerAuths = buyerAuthsService.getById(id);

        return R.ok().put("buyerAuths", buyerAuths);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BuyerAuthsEntity buyerAuths){
		buyerAuthsService.save(buyerAuths);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BuyerAuthsEntity buyerAuths){
		buyerAuthsService.updateById(buyerAuths);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		buyerAuthsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
