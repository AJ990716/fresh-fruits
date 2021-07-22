package com.fresh.fruits.api;

import com.fresh.fruits.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "fresh-fruits-goods-service")
public interface GoodsApi {

    /**
     * 查询指定类目下的6个商品
     */
    @RequestMapping("/goods/list/{category1_id}")
    public R list(@PathVariable("category1_id") Long category1_id);

}
