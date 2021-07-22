package com.fresh.fruits.itemcat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fresh.fruits.pojo.ItemCatEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类目
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-23 16:45:31
 */
@Mapper
public interface ItemCatDao extends BaseMapper<ItemCatEntity> {
	
}
