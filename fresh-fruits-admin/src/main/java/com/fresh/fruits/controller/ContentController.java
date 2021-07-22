package com.fresh.fruits.controller;

import com.fresh.fruits.api.ContentApi;
import com.fresh.fruits.pojo.Content;
import com.fresh.fruits.pojo.ContentCategory;
import com.fresh.fruits.pojo.PageParams;
import com.fresh.fruits.utils.FastDFSClient;
import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.controller
 * @CreateTime: 2021-06-16 09:55
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

    @ApiOperation(value = "分页获取广告列表", notes = "分页获取广告列表...")
    @PostMapping("listPage")
    public ResultCommon listPage(PageParams pageParams) {
        return ResultCommon.success(ResultCode.SUCCESS, contentApi.listPage(pageParams));
    }

    @ApiOperation(value = "根据ID查询广告数据", notes = "根据ID查询广告数据...")
    @ApiImplicitParam(name = "id", value = "广告ID")
    @GetMapping("findById/{id}")
    public ResultCommon findById(@PathVariable("id") Long id) {
        return ResultCommon.success(ResultCode.SUCCESS, contentApi.findById(id));
    }

    @ApiOperation(value = "根据ID新增广告数据", notes = "根据ID新增广告数据...")
    @ApiImplicitParam(name = "contentCategory", value = "广告实体")
    @PostMapping("add")
    public ResultCommon add(Content content) {
        //清除缓存
        redisTemplate.delete("contents_top");
        redisTemplate.delete("contents_middle");
        log.info("缓存清空....");
        return ResultCommon.success(ResultCode.SUCCESS, contentApi.add(content));
    }

    @ApiOperation(value = "根据ID更新广告数据", notes = "根据ID更新广告数据...")
    @ApiImplicitParam(name = "contentCategory", value = "广告分类实体")
    @PutMapping("update")
    public ResultCommon update(Content content) {
        redisTemplate.delete("contents_top");
        redisTemplate.delete("contents_middle");
        log.info("缓存清空....");
        try {
            Content oldcontent = contentApi.findById(content.getId());
            String oldcontentImage = oldcontent.getImage();
            if (!oldcontentImage.equals(content.getImage())) {
                //图片发生了更新，也就是说要删除原来的FastDFS服务器上的老的图片
                FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
                // http://172.81.235.217:8080/group1/M00/00/00/rBEACGDKuXaAElo6AACn3YKDeXI230.jpg
                String[] strings = oldcontentImage.split("/");
                fastDFSClient.deleteFile(strings[4] + "/" + strings[5] + "/" + strings[6] + "/" + strings[7], strings[3]);
                System.out.println("删除原始的图片：" + oldcontentImage);
            }
            return ResultCommon.success(ResultCode.SUCCESS, contentApi.update(content));
        } catch (Exception e) {
            return ResultCommon.success(ResultCode.FAIL);
        }
    }

    @ApiOperation(value = "根据ID删除广告数据", notes = "根据ID删除广告数据...")
    @ApiImplicitParam(name = "id", value = "广告ID")
    @DeleteMapping("delete/{id}")
    public ResultCommon delete(@PathVariable("id") Long id) {
        redisTemplate.delete("contents_top");
        redisTemplate.delete("contents_middle");
        log.info("缓存清空....");
        return ResultCommon.success(ResultCode.SUCCESS, contentApi.delete(id));
    }

}
