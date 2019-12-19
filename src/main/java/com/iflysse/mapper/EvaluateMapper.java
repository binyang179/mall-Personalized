package com.iflysse.mapper;

import com.iflysse.pojo.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评价映射类
 */
public interface EvaluateMapper {
    /**
     * 根据物品编号按分页获取所有的评价信息
     * @param pageSize 每页显示的条数
     * @param pageIndex 当前页码
     * @return 评价列表
     */
    public List<Evaluate> getByGood(@Param("gId") int goodsId,@Param("pSize") int pageSize, @Param("pIndex") int pageIndex);

    /**
     * 根据购物车编号获取对应物品的评价信息
     * @param cartId 购物车编号
     * @return 评价列表
     */
    public Evaluate getByCartId(@Param("cartId") int cartId);

    /**
     * 插入一条评价数据
     * @param userId 用户编号
     * @param goodsId 商品编号
     * @param cartId 购物车编号
     * @param grade 评分
     * @param comments 评价内容
     * @return
     */
    public void insertInfo(@Param("userId") int userId,@Param("goodsId") int goodsId,@Param("cartId") int cartId,
                           @Param("grade") double grade,@Param("comments") String comments);

}
