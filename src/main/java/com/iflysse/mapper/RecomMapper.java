package com.iflysse.mapper;

import com.iflysse.pojo.HotGoods;

import java.util.List;

/**
 * 商品映射类
 */
public interface RecomMapper {
    /**
     * 获取前10所有的热门商品信息
     * @return 商品列表
     */
    public String getRecommendationGoods(Integer userId);
}
