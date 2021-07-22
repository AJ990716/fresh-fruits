package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-24 16:37:38
 */
@Data
@TableName("tb_item")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 商品标题（SKU）
	 */
	private String title;
	/**
	 * 商品卖点
	 */
	private String sellPoint;
	/**
	 * 商品库存
	 */
	private Integer num;
	/**
	 * 商品图片
	 */
	private String image;
	/**
	 * 商品产地
	 */
	private String source;
	/**
	 * 商品规格
	 */
	private String spec;
	/**
	 * 商品数量
	 */
	private Integer stockCount;
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	/**
	 * 商城价格
	 */
	private BigDecimal marketPrice;
	/**
	 * 商品状态
	 */
	private String status;
	/**
	 * 商品类目
	 */
	private Long categoryid;
	/**
	 * 商品分类
	 */
	private String category;
	/**
	 * 商品名称（SPU）
	 */
	private Long goodsId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否启用（0：否、1：是）
	 */
	private String isDefault;

}
