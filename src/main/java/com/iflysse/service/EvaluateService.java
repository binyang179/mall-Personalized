package com.iflysse.service;

import com.iflysse.pojo.Evaluate;
import com.iflysse.viewmodel.EvaluateViewModel.EvaluateList;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;

import java.util.List;

/**
 * 商品服务类
 */
public interface EvaluateService {
    /**
     * 获取某个商品的所有评价信息
     * @param pageSize 每页大小
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */

    public  List<EvaluateList> getByGood(int goodsId, int pageSize, int pageIndex);

    /**
     * 根据购物车编号获取对应物品的评价信息
     * @param cartId 购物车编号
     * @return 评价列表
     */
    public EvaluateList getByCartId(int cartId);

    /**
     * 插入一条评价数据
     * @param data 评价信息
     * @return 评价列表
     */
    public void insertEvaluateInfo(Evaluate data);

}
