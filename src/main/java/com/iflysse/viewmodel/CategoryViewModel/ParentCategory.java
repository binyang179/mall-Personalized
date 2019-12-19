package com.iflysse.viewmodel.CategoryViewModel;

import com.iflysse.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应页面中的父级分类
 */
public class ParentCategory{
    private int id;
    private String title;
    private List<CategoryGroup> groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryGroup> getGroups(){
        return this.groups;
    }

    public void setGroups(List<CategoryGroup> groups){
        this.groups = groups;
    }
}
