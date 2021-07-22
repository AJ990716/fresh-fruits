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

import java.util.List;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.controller
 * @CreateTime: 2021-06-15 15:45
 * @Description: TODO
 */
@RestController
@RequestMapping("content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @Autowired
    ContentCategoryService contentCategoryService;

    @GetMapping("list")
    public List<Content> list(){
        return contentService.list(new QueryWrapper<Content>().eq("del",0));
    }

    /**
     * 分页查询
     * @return
     */
    @PostMapping("listPage")
    public PagerHelper<Content> listPage(@RequestBody PageParams pageParams){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("del",0);
        queryWrapper.orderByDesc("id");
        if(pageParams.getQueryData()!=null&&!"".equals(pageParams.getQueryData())){
            queryWrapper.like("title",pageParams.getQueryData());
        }
        IPage page = contentService.page(new Page<Content>(pageParams.getPageNumber(),pageParams.getPageSize()),queryWrapper);
        List<Content> records = page.getRecords();
        for (Content record : records) {
            Long categoryId = record.getCategoryId();
            String categoryName = contentCategoryService.getById(categoryId).getName();
            record.setCategoryName(categoryName);
        }
        //封装工具类
        PagerHelper<Content> pagerHelper=new PagerHelper<Content>(pageParams.getPageNumber(),pageParams.getPageSize(),page.getPages(),page.getTotal(),records);
        return pagerHelper;
    }

    @GetMapping("findById/{id}")
    public Content findById(@PathVariable("id") Long id){
        return contentService.getById(id);
    }

    @PostMapping("add")
    public Boolean add(@RequestBody Content content){
        return contentService.save(content);
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        Content content=new Content();
        content.setId(id);
        content.setDel(1);//删除状态
        return contentService.updateById(content);
    }

    @PutMapping("update")
    public Boolean update(@RequestBody Content content){
        return contentService.updateById(content);
    }

    @GetMapping("isExists/{category_id}")
    public Integer isExists(@PathVariable("category_id") Long category_id){
       return contentService.count(new QueryWrapper<Content>().eq("del",0).eq("category_id",category_id));
    }

    /**
     * 根据广告分类ID查询广告数据
     * @param category_id
     * @return
     */
    @GetMapping("contentsList/{category_id}")
    public List<Content> contentsList(@PathVariable("category_id") Long category_id){
        return contentService.list(new QueryWrapper<Content>().eq("del",0).eq("category_id",category_id)
                .orderByAsc("orders").last("limit 0,5"));
    }
}
