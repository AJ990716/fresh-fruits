package com.fresh.fruits.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fresh.fruits.pojo.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.fruits.service.GoodsService;
import com.fresh.fruits.utils.PageUtils;
import com.fresh.fruits.utils.R;



/**
 * 商品信息
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-24 16:37:38
 */
@RestController
@RequestMapping("goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 01-查询所有的上架的商品
     * @return
     */
    @RequestMapping("/findMarketableGoods")
    public List<GoodsEntity> findMarketableGoods(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("is_delete",0);
        queryWrapper.eq("is_marketable",1); //上架的商品
        return goodsService.list(queryWrapper);
    }

    /**
     * 查询指定类目下的6个商品
     */
    @RequestMapping("/list/{category1_id}")
    public R list(@PathVariable("category1_id") Long category1_id){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("category1_id",category1_id);
        queryWrapper.eq("is_delete",0);
        queryWrapper.eq("is_marketable",1); //上架的商品
        queryWrapper.last("limit 0,6");
        return R.ok().put("data", goodsService.list(queryWrapper));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		GoodsEntity goods = goodsService.getById(id);
        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GoodsEntity goods){
		goodsService.save(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GoodsEntity goods){
		goodsService.updateById(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		goodsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
