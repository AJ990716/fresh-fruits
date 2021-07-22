package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品描述
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-24 16:37:38
 */
@Data
@TableName("tb_goods_desc")
public class GoodsDescEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long goodsId;
	/**
	 * 商品描述
	 */
	private String introduction;
	/**
	 * 商品图片集
	 */
	private String itemImages;
	/**
	 * 商品规格集
	 */
	private String specificationItems;
	/**
	 * 自定义属性
	 */
	private String customAttributeItems;
	/**
	 * 包装列表
	 */
	private String packageList;
	/**
	 * 售后服务
	 */
	private String saleService;

}
