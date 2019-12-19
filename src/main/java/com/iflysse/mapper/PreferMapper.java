package com.iflysse.mapper;

import com.iflysse.pojo.Prefer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Prefer Mapper
 */
public interface PreferMapper {
    /**
     * 根据用户编号获取用户喜欢列表
     * @param userId 用户编号
     * @return 喜好列表
     */
    public List<Prefer> getByUserId(@Param("userId") int userId);

    /**
     * 插入一条用户喜欢数据
     * @param userId 用户编号
     * @param categoryId 父级分类编号
     * @return 喜好列表
     */
    public void insertInfo(@Param("userId") int userId, @Param("categoryId") int categoryId);


}
