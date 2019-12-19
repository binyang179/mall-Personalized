package com.iflysse.pojo;

import com.iflysse.util.Converter;
import com.iflysse.viewmodel.CategoryViewModel.ChildCategory;

public class ChildCategoryConvert implements Converter<Category, ChildCategory> {
    @Override
    public ChildCategory Convert(Category input) {
        ChildCategory child = new ChildCategory();
        child.setId(input.getId());
        child.setTitle(input.getTitle());
        child.setGroup(input.getGroup());
        return child;
    }
}
