package com.fresh.fruits.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fresh.fruits.dao.BuyerInfoDao;
import com.fresh.fruits.pojo.BuyerInfoEntity;
import com.fresh.fruits.service.BuyerInfoService;


@Service("buyerInfoService")
public class BuyerInfoServiceImpl extends ServiceImpl<BuyerInfoDao, BuyerInfoEntity> implements BuyerInfoService {

}