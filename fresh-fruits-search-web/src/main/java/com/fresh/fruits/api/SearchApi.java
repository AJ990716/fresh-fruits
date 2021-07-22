package com.fresh.fruits.api;

import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.utils.PagerHelper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fresh-fruits-search-service")
public interface SearchApi {

    /**
     * 搜索API
     * @return
     */
    @RequestMapping("/search/searchdata")
    public PagerHelper<GoodsEntity> listPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                             @RequestParam(value = "keywords", defaultValue = "") String keywords);
}
