package com.fresh.fruits.controller;

import com.fresh.fruits.api.GoodsApi;
import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.utils.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("goods")
@Slf4j
public class GoodsController {

    @Autowired
    GoodsApi goodsApi;

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "根据商品分类ID查询商品数据", notes = "根据商品分类ID查询商品数据...")
    @ApiImplicitParam(name = "category1_id", value = "商品分类ID")
    @RequestMapping("list/{category1_id}")
    public R showGoods(@PathVariable("category1_id") Long category1_id){
        List<GoodsEntity> goodsEntities=new ArrayList<GoodsEntity>();
        if("1001".equals(category1_id.toString())){
            goodsEntities = (List<GoodsEntity>) redisTemplate.opsForValue().get("goodsEntities_1001");
            if(goodsEntities==null||goodsEntities.size()==0){
                //查询数据库
                goodsEntities= (List<GoodsEntity>) goodsApi.list(1001L).get("data");
                //缓存一份
                redisTemplate.opsForValue().set("goodsEntities_1001", goodsEntities);
                log.info("商品生鲜数据查询的是数据库....");
            }else{
                log.info("商品生鲜数据查询的是缓存.....");
            }
        }
        return R.ok().put("data",goodsEntities);
    }
}
