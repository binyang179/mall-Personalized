package com.iflysse.service;

import com.iflysse.viewmodel.GoodsViewModel.Goods4List;

import java.util.List;

/**
 * 商品服务类
 */
public interface GoodsService {
    /**
     * 获取商品信息
     * @param pageSize 每页大小
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<Goods4List> getAll(int pageSize, int pageIndex);

    public List<Goods4List> getByCategory(int categoryId, int pageSize, int pageIndex);

    /**
     * 根据商品编号获取商品信息
     * @param goodsId 商品编号
     * @return 用于展示的商品列表
     */
    public Goods4List getById(int goodsId);

    /**
     * 根据商品名称获取指定商品信息
     * @param goodsName 商品编号
     * @param pageSize 每页大小
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<Goods4List> getByName(String goodsName, int pageSize, int pageIndex);


}
