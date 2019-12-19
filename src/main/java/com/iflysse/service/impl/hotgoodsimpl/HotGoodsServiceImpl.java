package com.iflysse.service.impl.hotgoodsimpl;

import com.iflysse.mapper.CategoryMapper;
import com.iflysse.mapper.GoodsMapper;
import com.iflysse.mapper.HotGoodsMapper;
import com.iflysse.pojo.Goods;
import com.iflysse.pojo.HotGoods;
import com.iflysse.service.HotGoodsService;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotGoodsServiceImpl implements HotGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private HotGoodsMapper hotGoodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 能够使用数据库完成的sql查询，就不要在代码中进行 build
     *
     * @param hotGoods
     * @return
     */

    private List<Goods4List> buildHotGoodsList(List<HotGoods> hotGoods){
        if(hotGoods==null || hotGoods.size()==0){
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        // 有代码错误 教学演示代码 不是一个线上代码
        for(HotGoods hotGood:hotGoods){
            Goods item=goodsMapper.getById(hotGood.getGoodsId());
            Goods4List g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private Goods4List buildGoods(Goods item){
        Goods4List result = new Goods4List();
        result.setId(item.getId());
        result.setName(item.getGoodsName());
        result.setPrice(item.getGoodsPrice());
        result.setUrl(item.getUrl());
        result.setDescription(item.getGoodsIntroduce());
        result.setCategoryId(item.getCategoryId());
        result.setCategory(categoryMapper.getCategoryById(item.getCategoryId()));
        result.setCategoryId(item.getCategoryId());

        return result;
    }

    @Override
    public List<Goods4List> getTop() {
        return buildHotGoodsList(hotGoodsMapper.getTop());
    }

    @Override
    public List<Goods4List> getNewsGoods() {
        return buildHotGoodsList(hotGoodsMapper.getNewsGoods());
    }
}
