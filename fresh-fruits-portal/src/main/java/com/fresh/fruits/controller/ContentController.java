package com.fresh.fruits.controller;

import com.fresh.fruits.api.ContentApi;
import com.fresh.fruits.pojo.Content;
import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.controller
 * @CreateTime: 2021-06-16 17:04
 * @Description: TODO
 */
@RestController
@RequestMapping("content")
@Slf4j
public class ContentController {

    @Autowired
    ContentApi contentApi;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据广告分类ID查询广告数据
     * @param category_id
     * @return
     */
    @ApiOperation(value = "根据分类ID查询广告数据", notes = "根据分类ID查询广告数据...")
    @ApiImplicitParam(name = "category_id", value = "分类ID")
    @GetMapping("/contentsList/{category_id}")
    public ResultCommon contentsList(@PathVariable("category_id") Long category_id){
        List<Content> contents=null;
        String redisKey="";
        if(category_id.toString().equals("1")){
            redisKey="contents_top";
            contents= (List<Content>) redisTemplate.opsForValue().get("contents_top");
        }else if(category_id.toString().equals("2")){
            redisKey="contents_middle";
            contents= (List<Content>) redisTemplate.opsForValue().get("contents_middle");
        }
        if(contents==null||contents.size()==0){
            //缓存中没有，需要查询数据库
            contents=contentApi.contentsList(category_id);
            //缓存中放一份
            redisTemplate.opsForValue().set(redisKey, contents);
            log.info("缓存中没有"+redisKey+"，先查询数据库，再放入缓存.....");
        }else{
            log.info("缓存中有"+redisKey+"，直接查询缓存.....");
        }
        return ResultCommon.success(ResultCode.SUCCESS,contents);
    }

}
