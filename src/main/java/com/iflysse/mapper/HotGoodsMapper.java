package com.iflysse.mapper;

import com.iflysse.pojo.HotGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品映射类
 */
public interface HotGoodsMapper {
    /**
     * 获取前10所有的热门商品信息
     * @return 商品列表
     */
    public List<HotGoods> getTop();

    public List<HotGoods> getNewsGoods();

}
