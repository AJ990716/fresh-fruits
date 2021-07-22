package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 买家拓展信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:22
 */
@Data
@TableName("tb_buyer_info")
public class BuyerInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 登录名称（曹晨磊）
	 */
	@TableId(value = "id")
	private String buyerId;
	/**
	 * 买家余额（0.00）
	 */
	private BigDecimal balance;
	/**
	 * 买家级别（1）
	 */
	private Integer level;
	/**
	 * 买家积分（9999）
	 */
	private Integer points;
	/**
	 * 买家经验（34224）
	 */
	private Integer experience;
	/**
	 * 买家爱好（tb_buyer_info.hobby-[]）
	 */
	private String hobby;
	/**
	 * 买家婚姻（0：未婚、1：已婚、2：保密）
	 */
	private String marriage;
	/**
	 * 买家收入（tb_buyer_info.income）
	 */
	private String income;
	/**
	 * 买家学历（0：初中、1：高中、2：中专、3：大专、4：本科、5：硕士、6：博士、7：其他）
	 */
	private String education;
	/**
	 * 买家行业（tb_buyer_info.industry）
	 */
	private String industry;
	/**
	 * 真实姓名（曹晨磊）
	 */
	private String realname;
	/**
	 * 身份证号（130124199712053319）
	 */
	private String idCard;

}
