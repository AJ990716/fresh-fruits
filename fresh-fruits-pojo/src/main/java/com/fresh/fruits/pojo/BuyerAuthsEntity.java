package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录授权
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:21
 */
@Data
@TableName("tb_buyer_auths")
public class BuyerAuthsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 授权主键（1）
	 */
	@TableId(value = "id")
	private Long id;
	/**
	 * 授权类型（0：手机、1：邮箱、2：用户、3：微信、4：微博、5：扣扣）
	 */
	private String type;
	/**
	 * 授权标识（曹晨磊）
	 */
	private String identifier;
	/**
	 * 授权凭据（123456）
	 */
	private String credential;
	/**
	 * 授权校验（0：未校验、1：已校验）
	 */
	private String verified;
	/**
	 * 授权状态（0：启用、1：关闭）
	 */
	private String enable;
	/**
	 * 登录地址（192.168.45.34）
	 */
	private String loginIp;
	/**
	 * 登录设备（0：电脑、1：平板、2：安卓、3：苹果）
	 */
	private String loginDevice;
	/**
	 * 登录类型（0：网页端、1：应用端、2：小程序）
	 */
	private String loginType;
	/**
	 * 登录时间（2019-08-18 00:00:00）
	 */
	private Date loginTime;
	/**
	 * 买家主键（曹晨磊）
	 */
	private String buyerId;

}
