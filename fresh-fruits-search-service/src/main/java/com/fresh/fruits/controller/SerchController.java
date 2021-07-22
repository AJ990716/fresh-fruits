package com.fresh.fruits.controller;

import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.pojo.ItemCatEntity;
import com.fresh.fruits.utils.PagerHelper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
public class SerchController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 搜索API
     * @return
     */
    @RequestMapping("searchdata")
    public PagerHelper<GoodsEntity> listPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                             @RequestParam(value = "keywords", defaultValue = "") String keywords){

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
        builder.withPageable(PageRequest.of(Integer.valueOf(pageNumber.toString()) - 1, Integer.valueOf(pageSize.toString())));
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
                    g.setSource(sourceAsMap.get("source").toString()); //产地
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
        //page 高亮之后的page
        List<GoodsEntity> goodsList = page.getContent();

        //封装工具类
        PagerHelper<GoodsEntity> pagerHelper=new PagerHelper<GoodsEntity>(pageNumber,pageSize,Long.valueOf(page.getTotalPages()),page.getTotalElements(),page.getContent());
        return pagerHelper;
    }

    /**
     * 搜索API
     * @return
     */
    @Deprecated //表示该方法已经过时
    @RequestMapping("oldsearchdata")
    public PagerHelper<GoodsEntity> oldListPage(@RequestParam(value = "pageNumber", defaultValue = "1") Long pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                               @RequestParam(value = "keywords", defaultValue = "") String keywords){

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if(keywords!=null&&!"".equals(keywords)){
            builder.withQuery(QueryBuilders.multiMatchQuery(keywords, "name", "caption"));
        }
        //设置分页的信息,页码从0开始计算
        builder.withPageable(PageRequest.of(Integer.valueOf(pageNumber.toString()) - 1, Integer.valueOf(pageSize.toString())));
        NativeSearchQuery searchQuery = builder.build();
        //条件查询分页，返回分页对象
        AggregatedPage<GoodsEntity> page = elasticsearchTemplate.queryForPage(searchQuery, GoodsEntity.class);

        //封装工具类
        PagerHelper<GoodsEntity> pagerHelper=new PagerHelper<GoodsEntity>(pageNumber,pageSize,Long.valueOf(page.getTotalPages()),page.getTotalElements(),page.getContent());
        return pagerHelper;
    }

}
