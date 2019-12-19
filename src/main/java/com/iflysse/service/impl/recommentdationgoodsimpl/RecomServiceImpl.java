package com.iflysse.service.impl.recommentdationgoodsimpl;

import com.iflysse.mapper.CategoryMapper;
import com.iflysse.mapper.GoodsMapper;
import com.iflysse.mapper.RecomMapper;
import com.iflysse.pojo.Goods;
import com.iflysse.service.RecomService;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RecomServiceImpl  implements RecomService {
    @Autowired
    private RecomMapper recomMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Goods4List> getRecommendationGoods(Integer userId) {
        return buildRecomGoodsList(recomMapper.getRecommendationGoods(userId));
    }

    private List<Goods4List> buildRecomGoodsList(String goodsIds){
        if(goodsIds==null || goodsIds.length()==0){
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        // 有代码错误 教学演示代码 不是一个线上代码
        List<Goods> reComGoods=goodsMapper.getByIds(goodsIds);
        for(Goods item:reComGoods){
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
}
