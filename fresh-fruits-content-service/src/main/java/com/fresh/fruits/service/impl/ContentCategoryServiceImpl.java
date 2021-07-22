package com.fresh.fruits.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fresh.fruits.mapper.ContentCategoryMapper;
import com.fresh.fruits.pojo.ContentCategory;
import com.fresh.fruits.service.ContentCategoryService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.service.impl
 * @CreateTime: 2021-06-15 10:16
 * @Description: TODO
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {
}
