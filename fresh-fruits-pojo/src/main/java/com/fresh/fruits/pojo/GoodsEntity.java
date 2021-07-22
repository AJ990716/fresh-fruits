package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-24 16:37:38
 */
@Data
@TableName("tb_goods")
//文档对象是(索引库名，类型)
@Document(indexName = "goodsindex")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	//字段的配置 类型 是否索引  是否存储
	@Field(store = true,index = false,type = FieldType.Integer)
	private Long id;

	/**
	 * 商品名称（SPU）
	 */
	@Field(store = true,analyzer = "ik_max_word",index = true,searchAnalyzer ="ik_max_word",type = FieldType.Text)
	private String name;

	/**
	 * 商品副标题
	 */
	@Field(store = true,analyzer = "ik_max_word",index = true,searchAnalyzer ="ik_max_word",type = FieldType.Text)
	private String caption;

	/**
	 * 商品图片
	 */
	@Field(store = true,index = false,type = FieldType.Text)
	private String image;

	/**
	 * 商品价格
	 */
	@Field(store = true,index = false,type = FieldType.Double)
	private Double price;

	/**
	 * 商品产地
	 */
	@Field(store = true,index = false,type = FieldType.Text)
	private String source;

	/**
	 * 商品一级类目
	 */
	@Transient //忽略该字段，不导入到ES中
	private Long category1Id;
	/**
	 * 商品二级类目
	 */
	@Transient //忽略该字段，不导入到ES中
	private Long category2Id;
	/**
	 * 商品三级类目
	 */
	@Transient //忽略该字段，不导入到ES中
	private Long category3Id;
	/**
	 * 商品模板
	 */
	@Transient //忽略该字段，不导入到ES中
	private Long typeTemplateId;
	/**
	 * 商品状态（0：未审核、1：已审核、2：已驳回、3：已删除）
	 */
	@Transient //忽略该字段，不导入到ES中
	private String status;
	/**
	 * 启用规格（0：否、1：是）
	 */
	@Transient //忽略该字段，不导入到ES中
	private String isEnableSpec;
	/**
	 * 是否上架（0：否、1：是）
	 */
	@Transient //忽略该字段，不导入到ES中
	private String isMarketable;
	/**
	 * 是否删除（0：否、1：是）
	 */
	@Transient //忽略该字段，不导入到ES中
	private String isDelete;

}
