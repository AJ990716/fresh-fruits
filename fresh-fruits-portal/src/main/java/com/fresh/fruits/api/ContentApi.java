package com.fresh.fruits.api;

import com.fresh.fruits.pojo.Content;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.utils.PagerHelper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.api
 * @CreateTime: 2021-06-15 15:50
 * @Description: TODO
 */
@FeignClient(value = "fresh-fruits-content-service")
public interface ContentApi {

    /**
     * 根据广告分类ID查询广告数据
     * @param category_id
     * @return
     */
    @GetMapping("/content/contentsList/{category_id}")
    public List<Content> contentsList(@PathVariable("category_id") Long category_id);

}
