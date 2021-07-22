package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品类目
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-06-23 16:45:31
 */
@Data
@TableName("tb_item_cat")
public class ItemCatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类目主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 类目名称
	 */
	private String name;
	/**
	 * 类目图片
	 */
	private String image;
	/**
	 * 上级类目
	 */
	private Long parentId;
	/**
	 * 模板类型
	 */
	private Long typeId;


	private Integer del=0;


	//表示他的子分类
	@TableField(exist = false)
	List<ItemCatEntity> childs;

}
