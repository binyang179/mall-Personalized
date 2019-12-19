package com.iflysse.mapper;

import com.iflysse.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Category Mapper
 */
public interface CategoryMapper {
    /**
     * 获取所有的一级分类信息
     * @return 一级分类列表
     */
    public List<Category> getParents();

    /**
     * 根据一级分类信息获取所有的分组信息
     * @param parentId 父类编号
     * @return 父类信息
     */
    public List<Category> getGroups(@Param("pId") int parentId);

    /**
     * 根据一级分类和分组信息查询对应的商品信息
     * @param parentId 父类编号
     * @param groupName 分类名称
     * @return 子类列表
     */
    public List<Category> getChildren(@Param("pId") int parentId, @Param("group") String groupName);

    /**
     * 根据分类编号查找分类信息
     * @param categoryId 分类编号
     * @return 父级编号
     */
    public Category getCategoryById(@Param("cId") int categoryId);
}
