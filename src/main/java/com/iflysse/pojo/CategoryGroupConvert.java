package com.iflysse.pojo;

import com.iflysse.util.Converter;
import com.iflysse.viewmodel.CategoryViewModel.CategoryGroup;

public class CategoryGroupConvert implements Converter<Category, CategoryGroup> {
    @Override
    public CategoryGroup Convert(Category input) {
        CategoryGroup group = new CategoryGroup();
        group.setGroupName(input.getGroup());
        return group;
    }
}
