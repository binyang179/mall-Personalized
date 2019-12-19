package com.iflysse.viewmodel.CategoryViewModel;

import com.iflysse.pojo.Category;

import java.util.List;

public class CategoryGroup {
    private String groupName;
    private List<ChildCategory> categories;

    /**
     * 重写equals函数
     *
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        CategoryGroup otherGroup = (CategoryGroup) other;
        return otherGroup.getGroupName() == this.getGroupName();
    }

    public int hashCode() {
        int result = 17;
        result += 31 * groupName.hashCode();
        result += 31 * categories.hashCode();
        return result;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ChildCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ChildCategory> categories) {
        this.categories = categories;
    }
}
