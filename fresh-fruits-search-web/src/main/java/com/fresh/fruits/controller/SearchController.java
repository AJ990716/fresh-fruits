package com.fresh.fruits.controller;

import com.fresh.fruits.api.SearchApi;
import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.utils.PagerHelper;
import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    SearchApi searchApi;

    /**
     * 同步请求搜索API
     * @param pageNumber
     * @param pageSize
     * @param keywords
     * @param model
     * @return
     */
    @RequestMapping("searchPage")
    public String searhPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                            @RequestParam(value = "keywords", defaultValue = "") String keywords, Model model){
        PagerHelper<GoodsEntity> pagerHelper = searchApi.listPage(pageNumber, pageSize, keywords);
        model.addAttribute("pagerHelper",pagerHelper);
        model.addAttribute("keywords",keywords); //存值
        return "search";
    }

    /**
     * 搜索API
     * @return
     */
    @RequestMapping("/searchdata")
    @ApiOperation(value = "商品搜索API", notes = "商品搜索API...")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "搜索的分页页码"),
            @ApiImplicitParam(name = "pageSize", value = "搜索的分页页面大小"),
            @ApiImplicitParam(name = "keywords", value = "搜索的关键词")
    })
    @ResponseBody
    public ResultCommon listPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                 @RequestParam(value = "keywords", defaultValue = "") String keywords){

        return ResultCommon.success(ResultCode.SUCCESS, searchApi.listPage(pageNumber,pageSize,keywords));
    }

}
