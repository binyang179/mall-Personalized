package com.iflysse.mapper;

import com.iflysse.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品映射类
 */
public interface GoodsMapper {
    /**
     * 按分页获取所有的商品信息
     * @param pageSize 每页显示的条数
     * @param pageIndex 当前页码
     * @return 商品列表
     */
    public List<Goods> getAll(@Param("pSize") int pageSize, @Param("pIndex") int pageIndex);

    /**
     * 根据分类编号分页获取指定商品列表
     * @param categoryId 分类编号
     * @param pageSize 每页大小
     * @param pageIndex 当前页码
     * @return 商品列表
     */
    public List<Goods> getByCategory(@Param("cId") int categoryId, @Param("pSize") int pageSize, @Param("pIndex") int pageIndex);

    /**
     * 根据商品编号获取指定商品信息
     * @param goodsId 商品编号
     * @return 商品信息
     */
    public Goods getById(@Param("gId") int goodsId);
    /**
     * 根据商品名称获取指定商品信息
     * @param goodsName 商品编号
     * @param pageSize 每页大小
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<Goods> getByName(@Param("gName") String goodsName, @Param("pSize") int pageSize, @Param("pIndex") int pageIndex);

    /**
     * 根据商品id的集合查询所有商品
     * @param ids 商品的集合  3465025,3465001,3452029,3452043,3451023,3505030,1459026,1687029,1587050,3481214
     * @return
     */
    public List<Goods> getByIds(@Param("goodsIds") String ids);
}
