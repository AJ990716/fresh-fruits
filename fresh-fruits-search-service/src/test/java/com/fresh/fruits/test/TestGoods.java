package com.fresh.fruits.test;

import com.fresh.fruits.SearchServiceApp;
import com.fresh.fruits.api.GoodsApi;
import com.fresh.fruits.controller.SerchController;
import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.service.GoodsEntityService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApp.class)
@Slf4j
public class TestGoods {

    @Autowired
    GoodsApi goodsApi;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    GoodsEntityService goodsEntityService;

    /**
     *01-创建一个索引库? Goods索引库，实际的项目搜索的是商品数据！
     */
    @Test
    public void testCreateIndex(){
        //创建索库,索引库的名字是??
        elasticsearchTemplate.createIndex(GoodsEntity.class);
        log.info("测试创建索引库成功~");
        elasticsearchTemplate.putMapping(GoodsEntity.class);//因为类上面有注解
        log.info("创建GoodsEntity的Mapping完成");
    }

    /**
     * 查询所有的上架的商品数据
     */
    @Test
    public void test1(){
        //查询所有的上架的商品
        List<GoodsEntity> marketableGoods = goodsApi.findMarketableGoods();
        //把所有上架的商品导入到索引库中，后期搜索使用
        goodsEntityService.saveDocuments(marketableGoods);
        log.info("Mysql中的数据导入到索引库成功.....");
    }

    /**
     * 根据ID查询
     */
    @Test
    public void testgetDocumentById(){
        GoodsEntity goods = goodsEntityService.getDocumentById(1000040L);
        System.out.println("查询的对象是:"+goods);
    }


    /**
     * 根据ID更新
     */
    @Test
    public void testUpdateById(){
        GoodsEntity goods = goodsEntityService.getDocumentById(1000040L);
        log.info("查询的原对象是:"+goods);
        goods.setName("阿玛尼装逼神器基围虾");
        goods.setCaption("阿玛尼装逼神器，泡妞必备,值得哟拥有基围虾基围虾基围虾");
        goods.setPrice(250.0);
        goodsEntityService.updateDocumentById(goods);
        log.info("更新document");
    }

    /**
     * 根据ID删除
     */
    @Test
    public void testDeleteByid(){
        goodsEntityService.deleteDocumentById(1000040L);
        log.info("删除成功1000040L");
    }

    /**
     * 删除所有
     */
    @Test
    public void testDeleteAll(){
        goodsEntityService.deleteAllDocument();
        log.info("全部干光了");
    }


    /**
     * 多条件搜索 分页
     */
    @Test
    public void testtestQuery2(){
        int pageIndex=1; //当前页码
        int pageSize=3; //页码大小

        String keywords="生长"; //用户输入的关键词

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if(keywords!=null&&!"".equals(keywords)){
            builder.withQuery(QueryBuilders.multiMatchQuery(keywords, "name", "caption"));
        }
        //设置分页的信息,页码从0开始计算
        builder.withPageable(PageRequest.of(pageIndex - 1, pageSize));
        NativeSearchQuery searchQuery = builder.build();

        //条件查询分页，返回分页对象
        AggregatedPage<GoodsEntity> page = elasticsearchTemplate.queryForPage(searchQuery, GoodsEntity.class);
        System.out.println("当前页码:"+pageIndex);
        System.out.println("页面大小:"+pageSize);
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("总条数:"+page.getTotalElements());
        System.out.println("每页到的数据是:");
        List<GoodsEntity> goodsList = page.getContent();
        for (GoodsEntity goods : goodsList) {
            System.out.println(goods);
        }
    }

    /**
     * 查询高亮
     */
    @Test
    public void testQueryHighLight(){

        int pageIndex=1; //当前页码
        int pageSize=3; //页码大小
        String keywords="进口牛肉"; //用户输入的关键词

        //设置高亮规则
        HighlightBuilder.Field nameField=new HighlightBuilder.Field("*") //所有出现的关键字
                .preTags("<span style='color:red'>") //关键字的前头
                .postTags("</span>") //关键的后头
                .requireFieldMatch(false);

        //构造搜索条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if(keywords!=null&&!"".equals(keywords)){
            builder.withQuery(QueryBuilders.multiMatchQuery(keywords, "name", "caption"));
        }

        //设置分页的信息,页码从0开始计算
        builder.withPageable(PageRequest.of(pageIndex - 1, pageSize));
        //设置搜搜的时候高亮
        builder.withHighlightFields(nameField);
        NativeSearchQuery searchQuery = builder.build();

        //在查询的时候使用 SearchResultMapper接口，对查询的结果进行修饰
        //条件查询分页，返回分页对象
        AggregatedPage<GoodsEntity> page = elasticsearchTemplate.queryForPage(searchQuery, GoodsEntity.class,new SearchResultMapper(){

            //对查询的结果集中的关键词进行包装
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                //获取搜索到的内容
                SearchHits searchHits = response.getHits();
                //数组
                SearchHit[] hits = searchHits.getHits();
                ArrayList<GoodsEntity> list = new ArrayList<GoodsEntity>();
                //循环数组
                for (SearchHit hit : hits) {
                    GoodsEntity g = new GoodsEntity();
                    //原始map
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    g.setId(Long.parseLong(sourceAsMap.get("id").toString())); //ID普通字段
                    g.setName(sourceAsMap.get("name").toString()); // 普通显示
                    g.setCaption(sourceAsMap.get("caption").toString()); // 普通副标题
                    g.setPrice(new Double(sourceAsMap.get("price").toString()));//商品价格
                    g.setImage(sourceAsMap.get("image").toString()); //商品图片
                    //高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.get("name") != null) {
                        String nameHighlight = highlightFields.get("name").getFragments()[0].toString();
                        g.setName(nameHighlight);
                    }
                    if (highlightFields.get("caption") != null) {
                        String contentHighlight = highlightFields.get("caption").getFragments()[0].toString();
                        g.setCaption(contentHighlight);
                    }
                    list.add(g);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }


            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        // page_withoutHighlight不高亮
        AggregatedPage<GoodsEntity> page_withoutHighlight = elasticsearchTemplate.queryForPage(searchQuery, GoodsEntity.class);//没有高亮
        System.out.println("当前页码:"+pageIndex);
        System.out.println("页面大小:"+pageSize);
        System.out.println("总页数:"+page_withoutHighlight.getTotalPages());
        System.out.println("总条数:"+page_withoutHighlight.getTotalElements());
        System.out.println("每页到的数据是:");

        //page 高亮之后的page
        List<GoodsEntity> goodsList = page.getContent();
        for (GoodsEntity goods : goodsList) {
            System.out.println(goods);
        }
    }
}
