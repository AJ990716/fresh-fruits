package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 密保问题
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:22
 */
@Data
@TableName("tb_buyer_question")
public class BuyerQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 问题主键
	 */
	@TableId(value = "id")
	private Long id;
	/**
	 * 问题标题
	 */
	private String title;
	/**
	 * 问题答案
	 */
	private String answer;
	/**
	 * 买家主键
	 */
	private String buyerId;

}
