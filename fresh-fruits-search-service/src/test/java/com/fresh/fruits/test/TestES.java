package com.fresh.fruits.test;

import com.fresh.fruits.SearchServiceApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApp.class)
public class TestES {

    //如果要操作Redis，需要获取一个 RedisTemplate
    //如果要操作ES，需要获取 ElasticsearchTemplate,天下我有！
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 01-测试工具类
     */
    @Test
    public void testConn(){
        System.out.println("ES操作的工具类:"+elasticsearchTemplate);
    }


    /**
     *02-创建一个索引库? Goods索引库，实际的项目搜索的是商品数据！
     */
    @Test
    public void testCreateIndex(){
        //创建索库
        elasticsearchTemplate.createIndex("goodsindexs");
        System.out.println("测试创建索引库成功");
    }


}
