package com.fresh.fruits.dao;

import com.fresh.fruits.pojo.BuyerInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 买家拓展信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:22
 */
@Mapper
public interface BuyerInfoDao extends BaseMapper<BuyerInfoEntity> {
	
}
