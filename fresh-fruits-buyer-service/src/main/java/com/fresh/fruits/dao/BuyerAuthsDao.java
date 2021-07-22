package com.fresh.fruits.dao;

import com.fresh.fruits.pojo.BuyerAuthsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录授权
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:21
 */
@Mapper
public interface BuyerAuthsDao extends BaseMapper<BuyerAuthsEntity> {
	
}
