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
@TableName("tb_content")
public class Content {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String title;
  private String url;
  private String image;
  private Long orders;
  private String status;
  private Long categoryId;
  private Integer del=0;

  @TableField(exist = false)
  private String categoryName;

}
