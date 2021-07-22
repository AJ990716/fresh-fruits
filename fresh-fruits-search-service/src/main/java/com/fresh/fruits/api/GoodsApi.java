package com.fresh.fruits.api;

import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "fresh-fruits-goods-service")
public interface GoodsApi {

    /**
     * 01-查询所有的上架的商品
     */
    @RequestMapping("/goods/findMarketableGoods")
    public List<GoodsEntity> findMarketableGoods();

}
