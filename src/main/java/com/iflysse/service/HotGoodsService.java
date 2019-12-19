package com.iflysse.service;

import com.iflysse.viewmodel.GoodsViewModel.Goods4List;

import java.util.List;

/**
 * 商品服务类
 */
public interface HotGoodsService {

    /**
     * 获取前10所有的热门商品信息
     * @return 商品列表
     */
    public List<Goods4List> getTop();

    /**
     * 获取前3条所有的热门商品信息
     * @return
     */
    public List<Goods4List> getNewsGoods();

}
