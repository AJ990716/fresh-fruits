package com.fresh.fruits.api;

import com.fresh.fruits.pojo.Content;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.utils.PagerHelper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.api
 * @CreateTime: 2021-06-15 15:50
 * @Description: TODO
 */
@FeignClient(value = "fresh-fruits-content-service")
public interface ContentApi {

    @GetMapping("/content/isExists/{category_id}")
    public Integer isExists(@PathVariable("category_id") Long category_id);

    @PostMapping("/content/listPage")
    public PagerHelper<Content> listPage(@RequestBody PageParams pageParams);

    @GetMapping("/content/findById/{id}")
    public Content findById(@PathVariable("id") Long id);

    @PostMapping("/content/add")
    public Boolean add(@RequestBody Content content);

    @DeleteMapping("/content/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id);

    @PutMapping("/content/update")
    public Boolean update(@RequestBody Content content);
}
