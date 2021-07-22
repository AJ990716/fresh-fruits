package com.fresh.fruits.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fresh.fruits.dao.BuyerDao;
import com.fresh.fruits.pojo.BuyerEntity;
import com.fresh.fruits.service.BuyerService;


@Service("buyerService")
public class BuyerServiceImpl extends ServiceImpl<BuyerDao, BuyerEntity> implements BuyerService {

}