package com.fresh.fruits.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fresh.fruits.pojo.Content;
import com.fresh.fruits.pojo.ContentCategory;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.service.ContentCategoryService;
import com.fresh.fruits.service.ContentService;
import com.fresh.fruits.utils.PagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.controller
 * @CreateTime: 2021-06-15 10:17
 * @Description: TODO
 */
@RestController
@RequestMapping("content_category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    @Autowired
    ContentService contentService;

    @GetMapping("deleteList/{ids}")
    public Boolean deleteList(@PathVariable("ids")  String ids){
        String[] content_category_ids = ids.split(",");
        Collection<ContentCategory> contentCategories = contentCategoryService.listByIds(Arrays.asList(content_category_ids));
        for (ContentCategory contentCategory : contentCategories) {
            contentCategory.setDel(1);
        }
        //批量更新
       return contentCategoryService.updateBatchById(contentCategories);
    }

    /**
     * 分页查询
     * @return
     */
    @PostMapping("listPage")
    public PagerHelper<ContentCategory> listPage(@RequestBody PageParams pageParams){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("del",0);
        if(pageParams.getQueryData()!=null&&!"".equals(pageParams.getQueryData())){
            queryWrapper.like("name",pageParams.getQueryData());
        }
        IPage page = contentCategoryService.page(new Page<ContentCategory>(pageParams.getPageNumber(),pageParams.getPageSize()),queryWrapper);
        //封装工具类
        PagerHelper<ContentCategory> pagerHelper=new PagerHelper<ContentCategory>(pageParams.getPageNumber(),pageParams.getPageSize(),page.getPages(),page.getTotal(),page.getRecords());
        return pagerHelper;
    }

    /**
     * 测试全查
     * @return
     */
    @GetMapping("list")
    public List<ContentCategory> list(){
        List<ContentCategory> categories = contentCategoryService.list(new QueryWrapper<ContentCategory>().eq("del", 0));
        for (ContentCategory category : categories) {
            int count = contentService.count(new QueryWrapper<Content>().eq("category_id", category.getId()).eq("del", 0));
            if(count>0){
                category.setIsUsed(true);
            }
        }
        return categories;
    }

    @GetMapping("findById/{id}")
    public ContentCategory findById(@PathVariable("id") Long id){
        return contentCategoryService.getById(id);
    }

    @PostMapping("add")
    public Boolean add(@RequestBody ContentCategory contentCategory){
        return contentCategoryService.save(contentCategory);
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        ContentCategory contentCategory=new ContentCategory();
        contentCategory.setId(id);
        contentCategory.setDel(1);//删除状态
        return contentCategoryService.updateById(contentCategory);
    }

    @PutMapping("update")
    public Boolean update(@RequestBody ContentCategory contentCategory){
        return contentCategoryService.updateById(contentCategory);
    }

}
