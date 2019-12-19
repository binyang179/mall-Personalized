package com.iflysse.service.impl.categoryimpl;

import com.iflysse.mapper.CategoryMapper;
import com.iflysse.pojo.Category;
import com.iflysse.pojo.CategoryConvert;
import com.iflysse.pojo.CategoryGroupConvert;
import com.iflysse.pojo.ChildCategoryConvert;
import com.iflysse.service.CategoryService;
import com.iflysse.util.ListFilter;
import com.iflysse.viewmodel.CategoryViewModel.CategoryGroup;
import com.iflysse.viewmodel.CategoryViewModel.ChildCategory;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ParentCategory> getCategories() {
        List<Category> categories = categoryMapper.getParents();
        if(categories==null || categories.size()==0){
            return new ArrayList<ParentCategory>(0);
        }
        List<ParentCategory> result = new ArrayList<>();
        // 遍历父类
        for(Category category:categories){
            ParentCategory item = new ParentCategory();
            item.setId(category.getId());
            item.setTitle(category.getTitle());
            item.setGroups(new ArrayList<>());
            result.add(item);
            List<Category> groups = categoryMapper.getGroups(item.getId());
            // 构建group
            for(Category groupItem:groups){
                CategoryGroup group = new CategoryGroup();
                group.setGroupName(groupItem.getGroup());
                group.setCategories(new ArrayList<>());
                item.getGroups().add(group);
                // 构建item
                List<Category> children = categoryMapper.getChildren(item.getId(), group.getGroupName());
                for(Category childItem:children){
                    ChildCategory child = new ChildCategory();
                    child.setId(childItem.getId());
                    child.setTitle(childItem.getTitle());
                    group.getCategories().add(child);
                }
            }
        }
        return result;
    }
}
