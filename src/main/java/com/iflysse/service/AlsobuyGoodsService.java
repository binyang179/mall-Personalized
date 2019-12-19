package com.iflysse.service;

import com.iflysse.viewmodel.GoodsViewModel.Goods4List;

import java.util.List;

public interface AlsobuyGoodsService {
    /**
     * 获取前10所有的热门商品信息
     * @return 商品列表
     */
    public List<Goods4List> getAlsoBuyGoodsTop(Integer goodsId);
}
