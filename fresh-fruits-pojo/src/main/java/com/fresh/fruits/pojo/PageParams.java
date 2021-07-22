package com.fresh.fruits.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.pojo
 * @CreateTime: 2021-06-15 17:02
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParams {

    private Long pageNumber; //当前页码
    private Long pageSize; //页面大小
    private Object queryData; //查询的条件
}
