package com.fresh.fruits.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_content_category")
public class ContentCategory {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private String name;
  private Integer del=0;

  //这个字段是扩展字段
  @TableField(exist = false)
  private Boolean isUsed=false;


}
