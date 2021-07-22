package com.fresh.fruits.dao;

import com.fresh.fruits.pojo.BuyerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 买家基本信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:21
 */
@Mapper
public interface BuyerDao extends BaseMapper<BuyerEntity> {
	
}
