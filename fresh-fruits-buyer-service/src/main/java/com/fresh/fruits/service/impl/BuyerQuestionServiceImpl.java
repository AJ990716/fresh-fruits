package com.fresh.fruits.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fresh.fruits.dao.BuyerQuestionDao;
import com.fresh.fruits.pojo.BuyerQuestionEntity;
import com.fresh.fruits.service.BuyerQuestionService;


@Service("buyerQuestionService")
public class BuyerQuestionServiceImpl extends ServiceImpl<BuyerQuestionDao, BuyerQuestionEntity> implements BuyerQuestionService {

}