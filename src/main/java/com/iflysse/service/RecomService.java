package com.iflysse.service;

import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务类
 */

public interface RecomService {

    /**
     * 获取个性化推荐的商品信息
     * @return 商品列表
     */
    public List<Goods4List> getRecommendationGoods(Integer userId);
}
