package com.fresh.fruits.cache;

import com.fresh.fruits.api.ContentApi;
import com.fresh.fruits.api.GoodsApi;
import com.fresh.fruits.pojo.Content;
import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.utils.R;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 缓存预热: 在项目启动完毕之后，自动预热缓存
 */
@Component
@Slf4j
public class PrepareCache implements ApplicationRunner {

    @Autowired
    ContentApi contentApi;

    @Autowired
    GoodsApi goodsApi;

    @Autowired
    RedisTemplate redisTemplate;

    public void run(ApplicationArguments args) throws Exception {
        log.info("项目启动啦，开始进行缓存预热处理.........");
        //1.查询数据库中的需要被缓存的数据
        //顶部轮播图
        List<Content> contents_top = contentApi.contentsList(1L);
        log.info("顶部轮播图:"+contents_top);
        //中部轮播图
        List<Content> contents_middle = contentApi.contentsList(2L);
        log.info("中部轮播图:"+contents_middle);
        //缓存商品数据
        R r = goodsApi.list(1001L);
        List<GoodsEntity> goodsEntities_1001= (List<GoodsEntity>) r.get("data");
        log.info("商品生鲜数据:"+goodsEntities_1001);

        //2.把数据加入到Redis的缓存中
        redisTemplate.opsForValue().set("contents_top", contents_top);
        redisTemplate.opsForValue().set("contents_middle", contents_middle);
        redisTemplate.opsForValue().set("goodsEntities_1001", goodsEntities_1001);

        log.info("项目启动啦，缓存预热完毕.........");
    }
}
