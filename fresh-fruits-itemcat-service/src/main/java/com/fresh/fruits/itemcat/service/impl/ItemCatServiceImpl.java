package com.fresh.fruits.itemcat.service.impl;

import com.fresh.fruits.pojo.ItemCatEntity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fresh.fruits.itemcat.dao.ItemCatDao;
import com.fresh.fruits.itemcat.service.ItemCatService;


@Service("itemCatService")
public class ItemCatServiceImpl extends ServiceImpl<ItemCatDao, ItemCatEntity> implements ItemCatService {



}