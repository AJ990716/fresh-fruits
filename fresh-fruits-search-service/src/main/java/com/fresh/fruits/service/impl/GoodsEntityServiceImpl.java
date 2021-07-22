package com.fresh.fruits.service.impl;

import com.fresh.fruits.dao.GoodsEntityDao;
import com.fresh.fruits.pojo.GoodsEntity;
import com.fresh.fruits.service.GoodsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsEntityServiceImpl implements GoodsEntityService {

    @Autowired
    GoodsEntityDao goodsEntityDao;

    public GoodsEntity saveDocument(GoodsEntity goods) {
        return goodsEntityDao.save(goods);
    }

    public Iterable<GoodsEntity> saveDocuments(List<GoodsEntity> list) {
        return goodsEntityDao.saveAll(list);
    }

    public GoodsEntity getDocumentById(Long id) {
        Optional<GoodsEntity> option = goodsEntityDao.findById(id);
        return option.get();
    }

    public void updateDocumentById(GoodsEntity goods) {
        goodsEntityDao.save(goods);//如果对象上有ID 那么就更新 如果没有ID 就新增
    }

    public void deleteDocumentById(Long id) {
        goodsEntityDao.deleteById(id);
    }

    public void deleteAllDocument() {
        goodsEntityDao.deleteAll();
    }
}
