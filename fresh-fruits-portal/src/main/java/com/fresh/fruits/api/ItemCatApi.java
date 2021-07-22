package com.fresh.fruits.api;

import com.fresh.fruits.pojo.ItemCatEntity;
import com.fresh.fruits.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "fresh-fruits-itemcat-service")
public interface ItemCatApi {

    /**
     * 列表-全部查询
     */
    @RequestMapping("/itemcat/list")
    public R list();

    /**
     * 根据parent_id查询数据
     * @param parent_id
     * @return
     */
    @RequestMapping("/itemcat/getItemcatsByParentId/{parent_id}")
    public List<ItemCatEntity> getItemcatsByParentId(@PathVariable("parent_id") Long parent_id);


}
