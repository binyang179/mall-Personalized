package com.iflysse.pojo;

import com.iflysse.util.Converter;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;

public class CategoryConvert implements Converter<Category, ParentCategory> {
    @Override
    public ParentCategory Convert(Category input) {
        ParentCategory parent = new ParentCategory();
        parent.setId(input.getId());
        parent.setTitle(input.getTitle());
        return parent;
    }
}
