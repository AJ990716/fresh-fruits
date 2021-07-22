package com.fresh.fruits.controller;

import com.fresh.fruits.api.ItemCatApi;
import com.fresh.fruits.pojo.ItemCatEntity;
import com.fresh.fruits.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("itemcat")
@Slf4j
public class ItemCatController {

    @Autowired
    ItemCatApi itemCatApi;


    /**
     * 列表-全部查询
     */
    @RequestMapping("/list")
    public R list(){
        //所有的一级分类
        List<ItemCatEntity> itemcats1 = itemCatApi.getItemcatsByParentId(0L);
        for (ItemCatEntity itemCatEntity : itemcats1) {
            //一级分类的ID就是二级分类parent_id
            Long id = itemCatEntity.getId();
            List<ItemCatEntity> itemcats2 = itemCatApi.getItemcatsByParentId(id);
            itemCatEntity.setChilds(itemcats2);
            for (ItemCatEntity catEntity : itemcats2) {
                Long id1 = catEntity.getId();
                List<ItemCatEntity> itemcats3 = itemCatApi.getItemcatsByParentId(id1);
                catEntity.setChilds(itemcats3);
            }
        }
        return R.ok().put("data",itemcats1);
    }

    /**
     * 根据parent_id查询类目集合
     * @param parent_id
     * @return
     */
    @RequestMapping("/getItemcatsByParentId/{parent_id}")
    public R getItemcatsByParentId(@PathVariable("parent_id") Long parent_id){
        List<ItemCatEntity> itemcats = itemCatApi.getItemcatsByParentId(parent_id);
        return R.ok().put("data",itemcats);
    }

}
