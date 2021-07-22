package com.fresh.fruits.api;

import com.fresh.fruits.pojo.ContentCategory;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.utils.PagerHelper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.api
 * @CreateTime: 2021-06-15 10:39
 * @Description: TODO
 */
@FeignClient(value = "fresh-fruits-content-service")
public interface ContentCategoryApi {

    @PostMapping("/content_category/listPage")
    public PagerHelper<ContentCategory> listPage(@RequestBody PageParams pageParams);

    @GetMapping("/content_category/list")
    public List<ContentCategory> list();

    @GetMapping("/content_category/findById/{id}")
    public ContentCategory findById(@PathVariable("id") Long id);

    @PostMapping("/content_category/add")
    public Boolean add(@RequestBody ContentCategory contentCategory);

    @DeleteMapping("/content_category/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id);

    @PutMapping("/content_category/update")
    public Boolean update(@RequestBody ContentCategory contentCategory);

    @GetMapping("/content_category/deleteList/{ids}")
    public Boolean deleteList(@PathVariable("ids") String ids);

}
