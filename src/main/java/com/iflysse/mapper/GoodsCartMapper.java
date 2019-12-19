package com.iflysse.mapper;

import com.iflysse.pojo.GoodsCart;
import com.iflysse.viewmodel.GoodsCartModel.GoodsCartList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Category Mapper
 */
public interface GoodsCartMapper {
    /**
     * 根据用户信息和状态按分页获取购物车信息
     * @param userId 用户编号
     * @param status 商品状态
     * @param pageSize 每页显示的条数
     * @param pageIndex 当前页码
     * @return 购物车列表
     */
    public List<GoodsCart> getByUser(@Param("userId") int userId,@Param("status") int status,@Param("pSize") int pageSize, @Param("pIndex") int pageIndex);

    /**
     * 根据购物车编号删除购物车信息
     * @param cartId 购物车编号
     * @return
     */
    public void deleteById(@Param("cartId") int cartId);

    /**
     * 根据购物车编号更新购物车信息（购买物品）
     * @param cartId 购物车编号
     * @param number 物品数量
     * @return
     */
    public void updateById(@Param("cartId") int cartId, @Param("number") int number);

    /**
     * 根据用户编号、物品编号和状态查找购物车信息
     * @param userId 用户编号
     * @param goodsId 物品编号
     * @param status 购物车状态
     * @return
     */
    public List<GoodsCart> getByInfo(@Param("userId") int userId, @Param("goodsId") int goodsId,@Param("status") int status);

    /**
     * 插入一条购物车数据
     * @param userId 用户编号
     * @param goodsId 物品编号
     * @param number 物品个数
     * @return
     */
    public void insertCartInfo(@Param("userId") int userId, @Param("goodsId") int goodsId,@Param("number") int number,@Param("status") int status);

    /**
     * 根据购物车编号添加购物的物品的数量
     * @param cartId 购物车编号
     * @return
     */
    public void addCartCountById(@Param("cartId") int cartId);

}
