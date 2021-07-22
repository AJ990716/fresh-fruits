package com.fresh.fruits.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fresh.fruits.dao.BuyerAuthsDao;
import com.fresh.fruits.pojo.BuyerAuthsEntity;
import com.fresh.fruits.service.BuyerAuthsService;


@Service("buyerAuthsService")
public class BuyerAuthsServiceImpl extends ServiceImpl<BuyerAuthsDao, BuyerAuthsEntity> implements BuyerAuthsService {

}