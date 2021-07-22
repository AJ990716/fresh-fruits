package com.fresh.fruits.api;

import com.fresh.fruits.pojo.ItemCatEntity;
import com.fresh.fruits.utils.PagerHelper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fresh-fruits-itemcat-service")
public interface ItemCatApi {

    /**
     * 分页查询
     * @return
     */
    @PostMapping("/itemcat/listPage")
    public PagerHelper<ItemCatEntity> listPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                               @RequestParam(value = "name", defaultValue = "") String name,
                                               @RequestParam(value = "parent_id", defaultValue = "0") Long parent_id);

    /**
     * 信息
     */
    @RequestMapping("/itemcat/info/{id}")
    public ItemCatEntity info(@PathVariable("id") Long id);
}
