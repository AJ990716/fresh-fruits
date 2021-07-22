package com.fresh.fruits.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fresh.fruits.dao.GoodsDescDao;
import com.fresh.fruits.pojo.GoodsDescEntity;
import com.fresh.fruits.service.GoodsDescService;


@Service("goodsDescService")
public class GoodsDescServiceImpl extends ServiceImpl<GoodsDescDao, GoodsDescEntity> implements GoodsDescService {

}