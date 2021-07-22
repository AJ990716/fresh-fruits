package com.fresh.fruits.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.fruits.pojo.BuyerEntity;
import com.fresh.fruits.service.BuyerService;
import com.fresh.fruits.utils.PageUtils;
import com.fresh.fruits.utils.R;



/**
 * 买家基本信息
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:21
 */
@RestController
@RequestMapping("buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok().put("page", buyerService.list());
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
		BuyerEntity buyer = buyerService.getById(id);

        return R.ok().put("buyer", buyer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BuyerEntity buyer){
		buyerService.save(buyer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BuyerEntity buyer){
		buyerService.updateById(buyer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
		buyerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
