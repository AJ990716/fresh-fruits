package com.fresh.fruits.dao;

import com.fresh.fruits.pojo.GoodsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-24 16:37:38
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsEntity> {
	
}
