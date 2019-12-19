package com.iflysse.service;

import com.iflysse.pojo.GoodsCart;
import com.iflysse.viewmodel.GoodsCartModel.GoodsCartList;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;

import java.util.List;

public interface GoodsCartService {
    /**
     * 根据用户信息和状态按分页获取购物车信息
     * @param userId 用户编号
     * @param status 商品状态
     * @param pageSize 每页显示的条数
     * @param pageIndex 当前页码
     * @return 购物车列表
     */
    public List<GoodsCartList> getByUser(int userId,int status,int pageSize, int pageIndex);

    /**
     * 根据购物车编号删除购物车信息
     * @param cartId 购物车编号
     * @return
     */
    public void deleteById(int cartId);

    /**
     * 根据购物车编号更新购物车信息（购买物品）
     * @param cartId 购物车编号
     * @param number 物品数量
     * @return
     */
    public void updateById(int cartId,int number);

    /**
     * 根据用户编号、物品编号和状态查找购物车信息
     * @param userId 用户编号
     * @param goodsId 物品编号
     * @param status 购物车状态
     * @return
     */
    public List<GoodsCartList> getByInfo(int userId, int goodsId, int status);

    /**
     * 插入一条购物车数据
     * @param userId 用户编号
     * @param goodsId 物品编号
     * @param number 物品个数
     * @param status 物品购买状态0购物车1已购买
     * @return
     */
    public void insertCartInfo(int userId, int goodsId,int number,int status);

    /**
     * 根据购物车编号更新购物的物品的数量
     * @param cartId 购物车编号
     * @return
     */
    public void addCartCountById (int cartId);

    /**
     * 根据用户编号获取已购买的商品信息以及评价信息
     * @param userId 用户编号
     * @param pageSize 每页显示的条数
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<GoodsCartList> getPurchasedGoodByUserId(int userId,int pageSize, int pageIndex);
}
