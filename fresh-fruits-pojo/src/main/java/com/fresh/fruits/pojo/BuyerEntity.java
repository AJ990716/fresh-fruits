package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 买家基本信息
 * 
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:21
 */
@Data
@TableName("tb_buyer")
public class BuyerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 登录名称（曹晨磊）
	 */
	@TableId(value = "id")
	private String id;
	/**
	 * 买家昵称（小狐狸Plus）
	 */
	private String nickname;
	/**
	 * 买家密码（123456a）
	 */
	private String password;
	/**
	 * 买家头像（http://image.shop.com/image/head.jpg）
	 */
	private String image;
	/**
	 * 买家性别（0：男、1：女、2：保密）
	 */
	private String gender;
	/**
	 * 买家生日（1997-12-05 00:00:00）
	 */
	private Date birthday;
	/**
	 * 买家地址（河北省-石家庄市-栾城区-冶河镇-端固庄村）
	 */
	private String address;
	/**
	 * 买家手机（15633029014）
	 */
	private String mobile;
	/**
	 * 买家邮箱（774908833@qq.com）
	 */
	private String email;
	/**
	 * 买家状态（0：正常、1：冻结）
	 */
	private String status;
	/**
	 * 创建时间（2019-08-18 00:00:00）
	 */
	private Date createTime;
	/**
	 * 更新时间（2019-08-18 00:00:00）
	 */
	private Date updateTime;

}
