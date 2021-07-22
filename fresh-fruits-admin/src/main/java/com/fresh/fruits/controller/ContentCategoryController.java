package com.fresh.fruits.controller;

import com.fresh.fruits.api.ContentApi;
import com.fresh.fruits.api.ContentCategoryApi;
import com.fresh.fruits.pojo.ContentCategory;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.utils.PagerHelper;
import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.controller
 * @CreateTime: 2021-06-15 10:40
 * @Description: TODO
 */
@RestController
@RequestMapping("content_category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryApi contentCategoryApi;

    @Autowired
    ContentApi contentApi;

    @ApiOperation(value = "分页获取广告分类列表", notes = "分页获取广告分类列表...")
    @PostMapping("listPage")
    public ResultCommon listPage(PageParams pageParams){
        return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.listPage(pageParams));
    }

    @ApiOperation(value = "获取广告分类列表", notes = "获取广告分类列表...")
    @GetMapping("list")
    public ResultCommon list(){
        return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.list());
    }

    @ApiOperation(value = "根据ID查询广告分类数据", notes = "根据ID查询广告分类数据...")
    @ApiImplicitParam(name = "id", value = "广告分类ID")
    @GetMapping("findById/{id}")
    public ResultCommon findById(@PathVariable("id") Long id){
        return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.findById(id));
    }

    @ApiOperation(value = "根据ID新增广告分类数据", notes = "根据ID新增广告分类数据...")
    @ApiImplicitParam(name = "contentCategory", value = "广告分类实体")
    @PostMapping("add")
    public ResultCommon add(ContentCategory contentCategory){
        return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.add(contentCategory));
    }

    @ApiOperation(value = "根据ID更新广告分类数据", notes = "根据ID更新广告分类数据...")
    @ApiImplicitParam(name = "contentCategory", value = "广告分类实体")
    @PutMapping("update")
    public ResultCommon update(ContentCategory contentCategory){
        return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.update(contentCategory));
    }

    @ApiOperation(value = "根据ID删除广告分类数据", notes = "根据ID删除广告分类数据...")
    @ApiImplicitParam(name = "id", value = "广告分类ID")
    @DeleteMapping("delete/{id}")
    public ResultCommon delete(@PathVariable("id") Long id){
        Integer exists = contentApi.isExists(id);
        if(exists==0){
            return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.delete(id));
        }else{
            return ResultCommon.success(ResultCode.NO_DELETE);
        }
    }

    @ApiOperation(value = "根据ID批量删除广告分类数据", notes = "根据ID批量删除广告分类数据...")
    @GetMapping("deleteList/{ids}")
    public ResultCommon deleteList(@PathVariable("ids") String ids){
        return ResultCommon.success(ResultCode.SUCCESS,contentCategoryApi.deleteList(ids));
    }
}
