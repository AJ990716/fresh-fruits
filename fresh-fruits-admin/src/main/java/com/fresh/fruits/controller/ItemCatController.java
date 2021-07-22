package com.fresh.fruits.controller;

import com.fresh.fruits.api.ItemCatApi;
import com.fresh.fruits.pojo.ItemCatEntity;
import com.fresh.fruits.utils.PagerHelper;
import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("itemcat")
@Slf4j
public class ItemCatController {

    @Autowired
    ItemCatApi itemCatApi;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("/listPage")
    public ResultCommon listPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam(value = "parent_id", defaultValue = "0") Long parent_id) {

        boolean flag=false;//是否是三级类目
        //动态生成页面的导航条
        String str = "<li><a href=\"javascript:queryData(0);\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顶级分类列表</a></li>";//顶级分类
        if (parent_id != 0) {
            //表示子分类
            ItemCatEntity item1 = itemCatApi.info(parent_id);//1级分类
            if (item1 != null) {
                //表示有2级分类
                String name1 = item1.getName(); //2级分类
                String str1 = "<li><a href=\"javascript:queryData("+item1.getId()+");\" >" + name1 + "</a></li>";
                Long parent_id1 = item1.getParentId();
                ItemCatEntity item2 = itemCatApi.info(parent_id1);
                if (item2 != null) {
                    String name2 = item2.getName();//3级分类
                    str = str + "<li><a href=\"javascript:queryData("+item2.getId()+");\" >" + name2 + "</a></li>";
                    flag= true; //表示已经是第三级类目
                }
                str = str + str1;
            }
        }
        log.info(str);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",flag);  //是否是三级分类
        map.put("str",str);   //；拼接的导航菜单

        PagerHelper<ItemCatEntity> pagerHelper = itemCatApi.listPage(pageNumber, pageSize, name, parent_id);
        pagerHelper.setPagedata(map);

        return ResultCommon.success(ResultCode.SUCCESS, pagerHelper);
    }


}
