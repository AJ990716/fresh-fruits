package com.fresh.fruits.itemcat.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fresh.fruits.pojo.ContentCategory;
import com.fresh.fruits.pojo.ItemCatEntity;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.utils.PageUtils;
import com.fresh.fruits.utils.PagerHelper;
import com.fresh.fruits.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fresh.fruits.itemcat.service.ItemCatService;


/**
 * 商品类目
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-23 16:45:31
 */
@RestController
@RequestMapping("itemcat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;


    /**
     * 分页查询
     * @return
     */
    @PostMapping("listPage")
    public PagerHelper<ItemCatEntity> listPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                               @RequestParam(value = "name", defaultValue = "") String name,
                                               @RequestParam(value = "parent_id", defaultValue = "0") Long parent_id){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("del",0);
        queryWrapper.eq("parent_id",parent_id);
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        IPage page = itemCatService.page(new Page<ItemCatEntity>(pageNumber,pageSize),queryWrapper);
        //封装工具类
        PagerHelper<ItemCatEntity> pagerHelper=new PagerHelper<ItemCatEntity>(pageNumber,pageSize,page.getPages(),page.getTotal(),page.getRecords());
        return pagerHelper;
    }


    /**
     * 根据parent_id查询数据
     * @param parent_id
     * @return
     */
    @RequestMapping("/getItemcatsByParentId/{parent_id}")
    public List<ItemCatEntity> getItemcatsByParentId(@PathVariable("parent_id") Long parent_id){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("del",0);
        queryWrapper.eq("parent_id",parent_id);
        return itemCatService.list(queryWrapper);
    }

    /**
     * 列表-全部查询
     */
    @RequestMapping("/list")
    public R list(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("del",0);
        //分页API....
        return R.ok().put("data", itemCatService.list(queryWrapper));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ItemCatEntity info(@PathVariable("id") Long id){
		ItemCatEntity itemCat = itemCatService.getById(id);
		return itemCat;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ItemCatEntity itemCat){
		itemCatService.save(itemCat);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ItemCatEntity itemCat){
		itemCatService.updateById(itemCat);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		itemCatService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
