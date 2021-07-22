package com.fresh.fruits.controller;

import java.util.Arrays;
import java.util.Map;

import com.fresh.fruits.pojo.GoodsDescEntity;
import com.fresh.fruits.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.fruits.service.GoodsDescService;



/**
 * 商品描述
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-24 16:37:38
 */
@RestController
@RequestMapping("goodsdesc")
public class GoodsDescController {
    @Autowired
    private GoodsDescService goodsDescService;

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
    @RequestMapping("/info/{goodsId}")
    public R info(@PathVariable("goodsId") Long goodsId){
		GoodsDescEntity goodsDesc = goodsDescService.getById(goodsId);
        return R.ok().put("goodsDesc", goodsDesc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GoodsDescEntity goodsDesc){
		goodsDescService.save(goodsDesc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GoodsDescEntity goodsDesc){
		goodsDescService.updateById(goodsDesc);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] goodsIds){
		goodsDescService.removeByIds(Arrays.asList(goodsIds));

        return R.ok();
    }

}
