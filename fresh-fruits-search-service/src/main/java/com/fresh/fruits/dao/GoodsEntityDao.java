package com.fresh.fruits.dao;

import com.fresh.fruits.pojo.GoodsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 专门操作ES服务器索引库的方法
 * Goods 实体类
 * Long  主键
 * ElasticsearchRepository 包含了操作索引库中常见的API方法
 * 相当于 BaseMapper
 */
@Repository
public interface GoodsEntityDao extends ElasticsearchRepository<GoodsEntity,Long> {

}
