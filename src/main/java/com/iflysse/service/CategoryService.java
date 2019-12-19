package com.iflysse.service;

import com.iflysse.viewmodel.CategoryViewModel.ChildCategory;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;

import java.util.List;

/**
 * 分类信息服务
 */
public interface CategoryService {
    /**
     * 获取所有的一级分类信息
     * @return 一级分类列表
     */
    public List<ParentCategory> getCategories();
}
