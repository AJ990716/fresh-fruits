package com.fresh.fruits.service;

import com.fresh.fruits.pojo.GoodsEntity;

import java.util.List;

public interface GoodsEntityService {

    //新增文档
    public GoodsEntity saveDocument(GoodsEntity goods);

    //批量新增
    public Iterable<GoodsEntity> saveDocuments(List<GoodsEntity> list);

    //根据ID查询文档
    public GoodsEntity getDocumentById(Long id);

    //根据ID更新
    public void updateDocumentById(GoodsEntity goods);

    //根据ID删除
    public void deleteDocumentById(Long id);

    //全部删除
    public void deleteAllDocument();
}
