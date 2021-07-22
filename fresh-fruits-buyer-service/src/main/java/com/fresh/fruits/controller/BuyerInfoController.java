package com.fresh.fruits.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.fruits.pojo.BuyerInfoEntity;
import com.fresh.fruits.service.BuyerInfoService;
import com.fresh.fruits.utils.PageUtils;
import com.fresh.fruits.utils.R;



/**
 * 买家拓展信息
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:22
 */
@RestController
@RequestMapping("buyerinfo")
public class BuyerInfoController {
    @Autowired
    private BuyerInfoService buyerInfoService;

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
    @RequestMapping("/info/{buyerId}")
    public R info(@PathVariable("buyerId") String buyerId){
		BuyerInfoEntity buyerInfo = buyerInfoService.getById(buyerId);

        return R.ok().put("buyerInfo", buyerInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BuyerInfoEntity buyerInfo){
		buyerInfoService.save(buyerInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BuyerInfoEntity buyerInfo){
		buyerInfoService.updateById(buyerInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] buyerIds){
		buyerInfoService.removeByIds(Arrays.asList(buyerIds));

        return R.ok();
    }

}
